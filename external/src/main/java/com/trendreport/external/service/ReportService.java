package com.trendreport.external.service;

import com.trendreport.external.dto.TrendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.client.AiClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final AiClient aiClient;
    private final String QUESTION = "트렌드 보고서를 작성해줘";

    public String getReport(TrendResponse response){
        return aiClient.generate(response.toString()+QUESTION);
    }
}
