package com.trendreport.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_REGISTERED_EMAIL(HttpStatus.BAD_REQUEST, "등록된 이메일입니다."),
    DO_NOT_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다."),
    DO_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비빌번호가 일치하지 않습니다."),
    DO_NOT_MATCH_ACCOUNT_INFO(HttpStatus.BAD_REQUEST, "계정정보가 일치하지 않습니다."),
    DELETED_ACCOUNT(HttpStatus.BAD_REQUEST,"삭제된 계정입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
