package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.dto.UserMissionDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.user.entity.User;

public class MissionConverter {
    public static MissionResponseDto.MissionInfo toMissionInfo(Mission mission){
        return MissionResponseDto.MissionInfo.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .createdAt(mission.getCreatedAt().toString())
                .build();
    }

    public static UserMissionDTO toUserMissionDto(UserMission um){
        return UserMissionDTO.builder().
                id(um.getMission().getId())
                .point(um.getMission().getPoint())
                .storeName(um.getMission().getStore().getName())
                .missionContent(um.getMission().getContent())
                .isCompleted(um.getIsCompleted())
                .build();
    }

}
