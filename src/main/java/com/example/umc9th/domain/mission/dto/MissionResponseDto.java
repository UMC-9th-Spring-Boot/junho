package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MissionResponseDto {
    private MissionResponseDto() {}

    @Builder
    @Getter
    @AllArgsConstructor
    public static class MissionInfo{
        private Long missionId;
        private String content;
        private String createdAt;
    }
}
