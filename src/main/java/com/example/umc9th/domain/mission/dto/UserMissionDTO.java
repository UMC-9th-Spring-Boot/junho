package com.example.umc9th.domain.mission.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserMissionDTO {
    private final Long id;
    private final Integer point;
    private final String storeName;
    private final String missionContent;
    private final boolean isCompleted;
}
