package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.global.dto.PageResponseDto;

public interface MissionService {
    String createMission(MissionRequestDto.CreateMission req);
    MissionResponseDto.MissionInfo addUserMission(Long missionId);
    PageResponseDto<MissionResponseDto.MissionInfo> getStoreMissions(Long storeId, Integer page, Integer size);
}
