package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;

public interface MissionService {
    String createMission(MissionRequestDto.CreateMission req);
    MissionResponseDto.AddUserMission addUserMission(Long missionId);
}
