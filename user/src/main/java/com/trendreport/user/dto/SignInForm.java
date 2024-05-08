package com.trendreport.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInForm {
    private String email;
    private String password;
}
