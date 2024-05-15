package com.trendreport.external.service;

import com.trendreport.external.dto.Ages;
import com.trendreport.external.dto.KeywordGroup;
import com.trendreport.external.dto.TimeUnit;
import com.trendreport.external.dto.TrendDto;
import com.trendreport.external.dto.TrendReportForm;
import com.trendreport.external.dto.TrendResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrendService {

    private final WebClient webClient;
    private final ZonedDateTime kst = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

    private final String NAVER_SEARCH_URL = "https://openapi.naver.com/v1/datalab/search";

    public TrendResponse getSearchTrend(TrendDto trendDto){
        return webClient.post()
            .uri(NAVER_SEARCH_URL)
            .bodyValue(trendDto)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                throw new RuntimeException("4xx");
            })
            .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                throw new RuntimeException("5xx");
            })
            .bodyToMono(TrendResponse.class)
            .block();
    }

    public TrendDto createTrendDto(TrendReportForm form){
        TimeUnit timeUnit = form.getTimeUnit();
        List<KeywordGroup> keywordGroup = form.getKeywordGroups();

        LocalDateTime now = kst.toLocalDateTime();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String endDate = now.format(dateFormat);
        String startDate = now.format(dateFormat);

        //주간
        if(timeUnit.toString().equals("date")){
            startDate = now.minusDays(7).format(dateFormat);
        }

        //월간
        if(timeUnit.toString().equals("week")){
            startDate = now.minusWeeks(4).format(dateFormat);
        }

        //연간
        if(timeUnit.toString().equals("month")){
            startDate = now.minusMonths(12).format(dateFormat);
        }

        return TrendDto.builder()
            .startDate(startDate)
            .endDate(endDate)
            .timeUnit(timeUnit.toString())
            .keywordGroups(keywordGroup)
            .device(form.getDevice().toString())
            .gender(form.getGender().toString())
            .ages(form.getAges().stream().map(Ages::toString).collect(Collectors.toList()))
            .build();
    }

}
