package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.mission.dto.UserMissionDTO;
import com.example.umc9th.global.dto.PageResponseDto;

public interface UserService {
    String deleteUser(Long userId);
    PageResponseDto<UserMissionDTO> getUserMissions(Boolean isCompleted, Integer page, Integer size);
    String completeMission(Long userMissionId);
}
