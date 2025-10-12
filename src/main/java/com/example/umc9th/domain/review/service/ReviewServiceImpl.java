package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService{
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public void createReview(String content, Long userId, Long storeId) {
        Review review = Review.builder()
                .content(content)
                .user(userRepository.getReferenceById(userId))
                .store(storeRepository.getReferenceById(storeId))
                .build();

        reviewRepository.save(review);
    }
}
