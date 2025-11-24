package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.dto.UserMissionDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.dto.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "사용자")
public interface UserControllerDocs {
    @Operation(
            summary = "내가 진행중인 미션 목록",
            description = "isCompleted에 따라 진행중/완료된 미션 목록을 조회합니다")
    public ApiResponse<PageResponseDto<UserMissionDTO>> getMyMissions(Boolean isCompleted, Integer page, Integer size);

    @Operation(
            summary = "미션 완료",
            description = "진행중인 사용자 미션을 진행 완료로 변경합니다")
    public ApiResponse<String> completeMission(Long userMissionId);

}
