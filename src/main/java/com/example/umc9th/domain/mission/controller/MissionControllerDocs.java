package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.dto.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "미션")
public interface MissionControllerDocs {

    @Operation(
            summary = "미션 추가",
            description = "관리자용")
    public ApiResponse<String> createMission(MissionRequestDto.CreateMission req);

    @Operation(
            summary = "사용자 미션 추가",
            description = "사용자가 가게 미션을 본인의 도전 미션에 추가합니다.")
    public ApiResponse<MissionResponseDto.MissionInfo> addMission(Long missionId);

    @Operation(
            summary = "특정 가게 미션 목록",
            description = "특정 가게 미션 목록을 조회합니다.")
    public ApiResponse<PageResponseDto<MissionResponseDto.MissionInfo>> getMissions(Long storeId, Integer page, Integer size);



}
