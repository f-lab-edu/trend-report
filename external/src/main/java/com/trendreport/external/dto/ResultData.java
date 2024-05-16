package com.trendreport.external.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResultData {
    private String period;
    private String ratio;
}
