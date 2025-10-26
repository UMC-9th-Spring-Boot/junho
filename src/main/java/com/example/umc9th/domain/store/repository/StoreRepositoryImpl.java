package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
// (수정) GroupBy import 제거
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.example.umc9th.domain.store.entity.QStore.store;

@Slf4j
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreQueryDsl{
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<StoreResponseDto.SearchedStore> searchStore(Predicate predicate, Long cursorId, Pageable pageable, String sortType) {
        BooleanBuilder builder = new BooleanBuilder(predicate);

        if(cursorId != null) {
            builder.and(store.id.lt(cursorId));
        }

        int pageSize = pageable.getPageSize();

        List<StoreResponseDto.SearchedStore> content = queryFactory
                .select(Projections.constructor(StoreResponseDto.SearchedStore.class,
                        store.id,
                        store.name,
                        store.address)
                )
                .from(store)
                .where(builder)
                .orderBy(StoreSort.getOrderList(sortType).toArray(new OrderSpecifier[0]))
                .limit(pageSize+1)
                .fetch();

        boolean hasNext = false;
        if (content.size() > pageSize) {
            content.remove(pageSize);
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}