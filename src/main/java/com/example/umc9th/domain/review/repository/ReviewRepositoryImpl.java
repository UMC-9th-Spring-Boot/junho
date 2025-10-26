package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.domain.review.entity.QReviewImage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.user.entity.QUser.user;
import static com.example.umc9th.domain.review.entity.QReviewImage.reviewImage;
import static com.example.umc9th.domain.review.entity.QReviewReply.reviewReply;

@Slf4j
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<ReviewResponseDto.Review> getReviews(Predicate predicate,
                                                      Long cursorId,
                                                      Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder(predicate);

        // 커서가 있으면
        if (cursorId != null) {
            builder.and(review.id.lt(cursorId)); // lt: less than
        }

        // 'size + 1' 만큼 조회 (다음 페이지 유무 확인용)
        int pageSize = pageable.getPageSize();

        List<ReviewResponseDto.Review> content = queryFactory
                .from(review)
                .leftJoin(review.user, user)
                .leftJoin(review.reviewImages, reviewImage)
                .leftJoin(review.reviewReply, reviewReply)
                .where(builder)
                .orderBy(review.id.desc())
                .limit(pageSize + 1)
                .transform( // transform은 자바 애플리케이션 메모리에서 sql 결과 리스트를 가공
                        GroupBy.groupBy(review.id).list(
                                Projections.constructor(ReviewResponseDto.Review.class,
                                        review.id,
                                        user.nickname,
                                        review.star,
                                        review.content,
                                        review.createdAt.stringValue(),
                                        GroupBy.list(reviewImage.imageUrl),
                                        reviewReply.content,
                                        reviewReply.createdAt.stringValue()
                                )
                        )
                );

        // hasNext (다음 페이지 존재 여부) 확인
        boolean hasNext = false;
        if (content.size() > pageSize) {
            content.remove(pageSize);
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}
