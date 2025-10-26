package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface StoreQueryDsl {
        Slice<StoreResponseDto.SearchedStore> searchStore(Predicate predicate, Long cursorId, Pageable pageable, String sortType);
}
