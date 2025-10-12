package com.example.umc9th.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyPageDTO {
    private final Long id;
    private final String email;
    private final Integer point;
    private final String phone;
}
