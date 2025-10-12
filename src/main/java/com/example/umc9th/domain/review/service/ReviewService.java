package com.example.umc9th.domain.review.service;

public interface ReviewService {
    void createReview(String content, Long userId, Long storeId);
}
