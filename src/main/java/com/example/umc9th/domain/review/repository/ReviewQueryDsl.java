package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Slice;

import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {
    Slice<ReviewResponseDto.Review> getReviews(Predicate predicate,
                                               Long cursorId,
                                               Pageable pageable);
}
