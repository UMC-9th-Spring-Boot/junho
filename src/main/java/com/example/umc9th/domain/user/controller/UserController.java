package com.example.umc9th.domain.user.controller;

import com.example.umc9th.domain.mission.dto.UserMissionDTO;
import com.example.umc9th.domain.user.service.UserService;
import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc9th.global.apiPayload.code.status.GeneralSuccessCode;
import com.example.umc9th.global.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserControllerDocs {
    private final UserService userService;

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable("userId") Long userId) {
        return ApiResponse.onSuccess(GeneralSuccessCode._DELETED, userService.deleteUser(userId));
    }

    @Override
    @GetMapping("/missions/{isCompleted}")
    public ApiResponse<PageResponseDto<UserMissionDTO>> getMyMissions(@PathVariable("isCompleted")Boolean isCompleted,
                                                                      @CheckPage Integer page,
                                                                      Integer size) {
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, userService.getUserMissions(isCompleted, page, size));
    }

    @PatchMapping("/{userMissionId}")
    public ApiResponse<String> completeMission(@PathVariable("userMissionId")Long userMissionId) {
        return ApiResponse.onSuccess(GeneralSuccessCode._OK, userService.completeMission(userMissionId));
    }
}
