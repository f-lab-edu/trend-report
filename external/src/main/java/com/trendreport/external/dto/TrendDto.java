package com.trendreport.external.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TrendDto {
    private String startDate;
    private String endDate;
    private String timeUnit;
    private List<KeywordGroup> keywordGroups;
    private String device;
    private String gender;
    private List<String> ages;
}
