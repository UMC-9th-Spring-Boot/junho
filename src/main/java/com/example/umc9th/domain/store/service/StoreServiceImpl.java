package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StorePredicate;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.example.umc9th.domain.store.repository.StoreRepositoryImpl;
import com.example.umc9th.global.dto.CursorResponseDto;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public CursorResponseDto<StoreResponseDto.SearchedStore> searchStore(String storeName, String region, Long cursorId, int size, String sortType) {
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(StorePredicate.storeNameContains(storeName));
        predicate.and(StorePredicate.storeAddressContains(region));

        Pageable pageable = PageRequest.of(0,size);

        Slice<StoreResponseDto.SearchedStore> slice = storeRepository.searchStore(predicate,cursorId,pageable, sortType);

        String nextCursor = null;
        if (slice.hasNext()) {
            List<StoreResponseDto.SearchedStore> content = slice.getContent();
            if (!content.isEmpty()) {
                nextCursor = content.get(content.size() - 1).getId().toString();
            }
        }

        return new CursorResponseDto<>(slice, nextCursor);
    }
}
