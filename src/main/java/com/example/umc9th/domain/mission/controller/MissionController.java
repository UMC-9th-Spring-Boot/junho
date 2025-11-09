package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.domain.review.dto.ReviewRequestDto;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.status.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Tag(name = "미션")
public class MissionController {
    private final MissionService missionService;

    @Operation(
            summary = "미션 추가",
            description = "관리자용")
    @PostMapping("/admin")
    public ApiResponse<String> addMission(@RequestBody MissionRequestDto.CreateMission req) {
        return ApiResponse.onSuccess(GeneralSuccessCode._CREATED, missionService.createMission(req));
    }

    @Operation(
            summary = "사용자 미션 추가",
            description = "사용자가 가게 미션을 본인의 도전 미션에 추가합니다.")
    @PostMapping("/{mission-id}")
    public ApiResponse<MissionResponseDto.AddUserMission> addMission(@RequestParam @PathVariable("mission-id")Long missionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode._CREATED, missionService.addUserMission(missionId));
    }
}
