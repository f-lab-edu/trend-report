package com.trendreport.external.dto;

import java.time.LocalDate;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private TimeUnit timeUnit;
    private List<TrendResponseResult> results;
}
