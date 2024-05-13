package com.trendreport.external.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrendReportForm {
    private String timeUnit;
    private List<KeywordGroup> keywordGroups;
}
