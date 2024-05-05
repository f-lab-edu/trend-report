package com.trendreport.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Sex {
    MAN("M"), WOMAN("W");
    private final String description;
}
