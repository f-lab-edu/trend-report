package com.trendreport.external.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrendResponseResult {
    private String title;
    private List<String> keywords;
    private List<ResultData> data;
}
