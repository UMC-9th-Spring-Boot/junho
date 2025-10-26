package com.example.umc9th.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreResponseDto {
    private StoreResponseDto() {}

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchedStore{
        private Long id;
        private String name;
        private String address;
    }
}
