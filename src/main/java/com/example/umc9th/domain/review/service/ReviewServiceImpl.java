package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewPredicate;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.repository.StorePredicate;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.user.repository.UserRepository;
import com.example.umc9th.global.dto.CursorResponseDto;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

    @Override
    public CursorResponseDto<ReviewResponseDto.Review> getReviews(String storeName,
                                                                  Float minStar,
                                                                  Float maxStar,
                                                                  Long cursorId,
                                                                  int size) {
        // 1. 서비스에서 Predicate 조합
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(ReviewPredicate.userIdEquals(1L));// todo: 로그인한 사용자
        predicate.and(StorePredicate.storeNameContains(storeName));
        predicate.and(ReviewPredicate.starRange(minStar, maxStar));

        // 2. Pageable 객체 생성
        Pageable pageable = PageRequest.of(0, size); // 커서 기반이므로 page는 0

        Slice<ReviewResponseDto.Review> slice = reviewRepository.getReviews(
                predicate,
                cursorId,
                pageable
        );

        // nextCursor 계산
        String nextCursor = null;
        if (slice.hasNext()) {
            List<ReviewResponseDto.Review> content = slice.getContent();
            if (!content.isEmpty()) {
                nextCursor = content.get(content.size() - 1).getId().toString();
            }
        }

        // 5. CursorResponseDto 반환
        return new CursorResponseDto<>(slice, nextCursor);
    }


}
