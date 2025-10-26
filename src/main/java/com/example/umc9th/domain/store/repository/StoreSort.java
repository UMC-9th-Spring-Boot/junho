package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;

import java.util.ArrayList;
import java.util.List;

public class StoreSort {
    private static final QStore store = QStore.store;

    public static List<OrderSpecifier<?>> getOrderList(String sortType) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        switch(sortType.toLowerCase()) {
            case "latest":
                orders.add(store.createdAt.desc());
                return orders;
            case "name":
                OrderSpecifier<Integer> sortPriority = new CaseBuilder()
                        // [수정] mysqlRegexMatches 헬퍼 메서드 사용
                        .when(mysqlRegexMatches(store.name, "^[가-힣]")).then(1)
                        .when(mysqlRegexMatches(store.name, "^[A-Z]")).then(2)
                        .when(mysqlRegexMatches(store.name, "^[a-z]")).then(3)
                        .otherwise(4)
                        .asc();

                orders.add(sortPriority);
                orders.add(store.name.asc());
                orders.add(store.createdAt.desc());

                return orders;
            default:
                return null;

        }
    }

    /**
     * HQL의 'function()'을 사용해 MySQL의 'REGEXP_LIKE' 함수를 호출합니다.
     * MySQL에서 REGEXP_LIKE는 true일 때 1, false일 때 0을 반환합니다.
     *
     * @param path    QStore.store.name
     * @param pattern 정규표현식 (예: "^[가-힣]")
     * @return BooleanExpression (예: function('REGEXP_LIKE', store.name, '...') = 1)
     */
    private static BooleanExpression mysqlRegexMatches(StringPath path, String pattern) {
        // 1. HQL 템플릿 생성
        NumberExpression<Integer> regexpFunction = Expressions.numberTemplate(
                Integer.class,
                // [핵심 수정] 'REGEXP' -> 'REGEXP_LIKE'
                "function('REGEXP_LIKE', {0}, {1})",
                path,
                pattern
        );

        // 2. HQL 파서가 이해할 수 있는 BooleanExpression 반환
        //    (function(...) = 1)
        return regexpFunction.eq(1);
    }
}