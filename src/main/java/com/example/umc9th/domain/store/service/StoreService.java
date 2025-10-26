package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.example.umc9th.global.dto.CursorResponseDto;

public interface StoreService {
    CursorResponseDto<StoreResponseDto.SearchedStore> searchStore(String storeName,
                                                                  String region,
                                                                  Long cursorId,
                                                                  int size,
                                                                  String sortType);
}
