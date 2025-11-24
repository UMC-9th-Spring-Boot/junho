package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionService;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.status.GeneralSuccessCode;
import com.example.umc9th.global.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController implements MissionControllerDocs{
    private final MissionService missionService;

    @PostMapping("/admin")
    public ApiResponse<String> createMission(@RequestBody MissionRequestDto.CreateMission req) {
        return ApiResponse.onSuccess(GeneralSuccessCode._CREATED, missionService.createMission(req));
    }

    @PostMapping("/{mission-id}")
    public ApiResponse<MissionResponseDto.MissionInfo> addMission(@PathVariable("mission-id")Long missionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode._CREATED, missionService.addUserMission(missionId));
    }

    @GetMapping("/{store-id}")
    public ApiResponse<PageResponseDto<MissionResponseDto.MissionInfo>> getMissions(@PathVariable("store-id")Long storeId,
                                                             @CheckPage Integer page,
                                                             Integer size) {
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, missionService.getStoreMissions(storeId, page, size));
    }


}
