package com.example.umc9th.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static com.example.umc9th.domain.review.entity.QReview.review;
import static com.example.umc9th.domain.store.entity.QStore.store;

public class ReviewPredicate {

    private ReviewPredicate() {}

    public static BooleanExpression storeNameContains(String storeName) {
        return StringUtils.hasText(storeName) ? store.name.contains(storeName) : null;
    }

    public static BooleanExpression starRange(Float minStar, Float maxStar) {
        if (minStar != null && maxStar != null) {
            return review.star.between(minStar, maxStar);
        } else if (minStar != null) {
            return review.star.goe(minStar); // goe: great or equal
        } else if (maxStar != null) {
            return review.star.loe(maxStar); // loe: low or equal
        }

        return null;
    }

    public static BooleanExpression userIdEquals(Long userId) {
        return userId != null ? review.user.id.eq(userId) : null;
    }
}
