package com.example.umc9th.global.apiPayload.exception.handler;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class GlobalHandler extends GeneralException {
    public GlobalHandler(BaseErrorCode code) {
        super(code);
    }
}
