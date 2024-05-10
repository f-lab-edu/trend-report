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
    DO_NOT_MATCH_TOPIC(HttpStatus.BAD_REQUEST, "일치하는 토픽이 없습니다."),
    ALREADY_FULL_INTEREST(HttpStatus.BAD_REQUEST, "관심사가 다 찼습니다."),
    EXIST_INTEREST(HttpStatus.BAD_REQUEST, "존재하는 관심사입니다."),
    DO_NOT_EXIST_TOPIC(HttpStatus.BAD_REQUEST, "존재하지 않는 토픽입니다."),
    DO_NOT_EXIST_INTEREST(HttpStatus.BAD_REQUEST, "존재하지 않는 관심사입니다."),
    DO_NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),
    DELETED_ACCOUNT(HttpStatus.BAD_REQUEST,"삭제된 계정입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
