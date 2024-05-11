package com.trendreport.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class InterestDto {
    private String topic;

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        InterestDto that = (InterestDto) o;
        return topic.equals(that.topic);
    }
}
