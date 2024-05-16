package com.trendreport.external.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TrendResponseResult {
    private String title;
    private List<String> keywords;
    private List<ResultData> data;
}
