package com.trendreport.external.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrendReportForm {
    @Enumerated(EnumType.STRING)
    private TimeUnit timeUnit;

    private List<KeywordGroup> keywordGroups;

    @Enumerated(EnumType.STRING)
    private Device device;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private List<Ages> ages;
}
