package com.trendreport.external.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trendreport.external.dto.TimeUnit;
import com.trendreport.external.dto.TrendDto;
import com.trendreport.external.dto.TrendReportForm;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrendServiceTest {

    @InjectMocks
    private TrendService trendService;


    @Test
    void createTrendDto_date() {
        //given
        TrendReportForm form = TrendReportForm.builder()
            .timeUnit(TimeUnit.date)
            .keywordGroups(new ArrayList<>())
            .build();
        String startDate = LocalDateTime.now().minusDays(7)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //when
        TrendDto trendDto = trendService.createTrendDto(form);

        //then
        assertEquals(startDate,trendDto.getStartDate());
    }
    @Test
    void createTrendDto_week() {
        //given
        TrendReportForm form = TrendReportForm.builder()
            .timeUnit(TimeUnit.week)
            .keywordGroups(new ArrayList<>())
            .build();
        String startDate = LocalDateTime.now().minusWeeks(4)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //when
        TrendDto trendDto = trendService.createTrendDto(form);

        //then
        assertEquals(startDate,trendDto.getStartDate());
    }
    @Test
    void createTrendDto_month() {
        //given
        TrendReportForm form = TrendReportForm.builder()
            .timeUnit(TimeUnit.month)
            .keywordGroups(new ArrayList<>())
            .build();
        String startDate = LocalDateTime.now().minusMonths(12)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //when
        TrendDto trendDto = trendService.createTrendDto(form);

        //then
        assertEquals(startDate,trendDto.getStartDate());
    }
}