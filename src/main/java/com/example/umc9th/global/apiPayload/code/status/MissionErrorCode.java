package com.example.umc9th.global.apiPayload.code.status;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    ALREADY_COMPLETED(HttpStatus.BAD_REQUEST,"USER_MISSION4001","이미 완료된 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
