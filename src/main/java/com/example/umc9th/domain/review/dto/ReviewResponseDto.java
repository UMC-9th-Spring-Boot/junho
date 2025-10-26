package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResponseDto {
    // 객체 생성 방지
    private ReviewResponseDto() {}

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Review{
        private Long id;
        private String nickname;
        private Float star;
        private String content;
        private String createdAt;
        private List<String> reviewImages;

        private String reviewReplyContent;
        private String reviewReplyCreatedAt;
    }
}
