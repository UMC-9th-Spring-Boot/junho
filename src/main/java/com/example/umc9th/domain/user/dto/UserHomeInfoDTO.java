package com.example.umc9th.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserHomeInfoDTO {
    private final Integer point;
    private final Long completedMissionCount;
}
