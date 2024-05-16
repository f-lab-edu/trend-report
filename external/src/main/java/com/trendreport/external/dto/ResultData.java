package com.trendreport.external.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResultData {
    private String period;
    private String ratio;
}
