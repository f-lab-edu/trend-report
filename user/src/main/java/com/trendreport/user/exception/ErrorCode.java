package com.trendreport.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_EMAIL(HttpStatus.BAD_REQUEST, "등록된 이메일입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
