package com.example.umc9th.domain.mission.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MissionInAreaDTO {
    private final String storeName;
    private final LocalDateTime createdAt;
    private final String missionContent;
    private final Integer point;
}
