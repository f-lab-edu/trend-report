package com.trendreport.external.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrendResponse {
    private LocalDate startDate;
    private LocalDate endDate;
    private TimeUnit timeUnit;
    private List<TrendResponseResult> results;
}
