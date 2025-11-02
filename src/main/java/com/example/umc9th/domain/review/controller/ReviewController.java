package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.status.GeneralSuccessCode;
import com.example.umc9th.global.dto.CursorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "리뷰")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "리뷰 조회",
            description = "가게별, 별점별로 리뷰를 조회합니다.")
    @GetMapping
    public ApiResponse<CursorResponseDto<ReviewResponseDto.Review>> getReviews(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false, defaultValue = "0.0") Float minStar,
            @RequestParam(required = false, defaultValue = "5.0") Float maxStar,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        return ApiResponse.onSuccess(GeneralSuccessCode._OK,reviewService.getReviews(storeName,
                minStar,
                maxStar,
                cursorId,
                size));
    }

}
