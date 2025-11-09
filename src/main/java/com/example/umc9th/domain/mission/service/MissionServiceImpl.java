package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public String createMission(MissionRequestDto.CreateMission req) {
        Mission mission = Mission.builder()
                .store(storeRepository.getReferenceById(1L))
                .content(req.getContent())
                .point(req.getPoint())
                .build();

        missionRepository.save(mission);

        return mission.getId().toString();
    }

    @Override
    public MissionResponseDto.AddUserMission addUserMission(Long missionId) {
        UserMission um = UserMission.builder()
                .user(userRepository.getReferenceById(1L))
                .mission(missionRepository.getReferenceById(missionId))
                .build();

        userMissionRepository.save(um);

        return MissionResponseDto.AddUserMission.builder()
                .missionId(missionId)
                .content(um.getMission().getContent())
                .createdAt(um.getMission().getCreatedAt().toString())
                .build();
    }
}
