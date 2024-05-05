package com.trendreport.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpForm {
    private String email;
    private String password;
    private Integer sex;
    private Integer age;
}
