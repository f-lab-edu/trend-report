package com.trendreport.external.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Ages {
    RANGE_0_TO_12(1),
    RANGE_13_TO_18(2),
    RANGE_19_TO_24(3),
    RANGE_25_TO_29(4),
    RANGE_30_TO_34(5),
    RANGE_35_TO_39(6),
    RANGE_40_TO_44(7),
    RANGE_45_TO_49(8),
    RANGE_50_TO_54(9),
    RANGE_55_TO_59(10),
    RANGE_60_AND_ABOVE(11);

    private final int value;

    public String toString(){
        return String.valueOf(this.getValue());
    }
}
