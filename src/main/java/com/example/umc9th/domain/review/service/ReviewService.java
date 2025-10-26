package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.global.dto.CursorResponseDto;

public interface ReviewService {
    void createReview(String content, Long userId, Long storeId);

    CursorResponseDto<ReviewResponseDto.Review> getReviews(String storeName,
                                                           Float minStar,
                                                           Float maxStar,
                                                           Long cursorId,
                                                           int size);
}
