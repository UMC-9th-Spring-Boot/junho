package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.inqury.repository.InquiryRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.UserMissionDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.user.repository.*;
import com.example.umc9th.global.apiPayload.code.status.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.code.status.MissionErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import com.example.umc9th.global.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final InquiryRepository inquiryRepository;
    private final ReviewRepository reviewRepository;
    private final UserCategoryRepository userCategoryRepository;
    private final PhoneVerificationRepository phoneVerificationRepository;
    private final NotificationAgreementRepository notificationAgreementRepository;
    private final TermAgreementRepository termAgreementRepository;

    @Override
    @Transactional
    public String deleteUser(Long userId) {
        userMissionRepository.deleteByUserId(userId);
        inquiryRepository.deleteByUserId(userId);
        reviewRepository.deleteByUserId(userId);
        userCategoryRepository.deleteByUserId(userId);
        phoneVerificationRepository.deleteByUserId(userId);
        notificationAgreementRepository.deleteByUserId(userId);
        termAgreementRepository.deleteByUserId(userId);
        userRepository.softDeleteUser(userId);

        return "유저" + userId.toString() + "이 삭제되었습니다.";
    }

    @Override
    public PageResponseDto<UserMissionDTO> getUserMissions(Boolean isCompleted, Integer page, Integer size) {
        Page<UserMission> missions = userMissionRepository.findByUserIdAndIsCompleted(1L, isCompleted, PageRequest.of(page, size));
        Page<UserMissionDTO> result = missions.map(MissionConverter::toUserMissionDto);
        return new PageResponseDto<>(result);
    }

    @Override
    @Transactional
    public String completeMission(Long userMissionId) {
        UserMission um = userMissionRepository.findById(userMissionId)
                .orElseThrow(()-> new GeneralException(GeneralErrorCode.NO_RESULT));

        if(um.getIsCompleted()) {
            throw new GeneralException(MissionErrorCode.ALREADY_COMPLETED);
        }else{
            um.complete();
        }

        return um.getId().toString() + "번 미션이 완료되었습니다";
    }
}
