package com.example.umc9th.domain.store.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import static com.example.umc9th.domain.store.entity.QStore.store;

public class StorePredicate {
    private StorePredicate() {}

    public static BooleanExpression storeNameContains(String queryString) {
        if(queryString == null) return null;
        String[] keywords = queryString.trim().split("\\s+"); // 공백 여러 칸 이어도 나눔

        BooleanExpression be = null;

        for(String keyword : keywords) {
            if(be == null) {
                be = store.name.containsIgnoreCase(keyword); // 대소문자 구분 X
            }else{
                be = be.and(store.name.containsIgnoreCase(keyword));
            }
        }

        return be;
    }

    public static BooleanExpression storeAddressContains(String region) {
        return StringUtils.hasText(region) ? store.address.contains(region) : null;
    }
}
