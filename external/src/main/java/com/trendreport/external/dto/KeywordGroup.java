package com.trendreport.external.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KeywordGroup {
    private String groupName;
    private List<String> keywords;
}
