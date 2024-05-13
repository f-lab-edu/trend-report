package com.trendreport.external.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrendResponse {
    private String startDate;
    private String endDate;
    private String timeUnit;
    private List<TrendResponseResult> results;
}
