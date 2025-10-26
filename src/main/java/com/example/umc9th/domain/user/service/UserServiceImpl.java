package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.inqury.repository.InquiryRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.user.repository.*;
import lombok.RequiredArgsConstructor;
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
    public void deleteUser(Long userId) {
        userMissionRepository.deleteByUserId(userId);
        inquiryRepository.deleteByUserId(userId);
        reviewRepository.deleteByUserId(userId);
        userCategoryRepository.deleteByUserId(userId);
        phoneVerificationRepository.deleteByUserId(userId);
        notificationAgreementRepository.deleteByUserId(userId);
        termAgreementRepository.deleteByUserId(userId);
        userRepository.softDeleteUser(userId);
    }
}
