package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreResponseDto;
import com.example.umc9th.domain.store.service.StoreService;
import com.example.umc9th.global.dto.CursorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.registry.Registry;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
@Tag(name = "가게")
public class StoreController {
    private final StoreService storeService;

    @Operation(
            summary = "가게 검색",
            description = """
        1-1. 지역 필터링: region 기반, 다중 선택 가능
        1-2. 이름 검색:
             - 공백 포함: 각 단어 포함 가게 합집합 조회
             - 공백 없음: 전체 검색어 포함 가게 조회
        1-3. 정렬 조건:
             - latest: 최신순
             - name: 가나다 → 영어 대문자 → 영어 소문자 → 특수문자, 이름 동일 시 최신순
        1-4. 페이징: page + size (커서 기반 페이징 옵션 가능)
    """)
    @GetMapping
    public CursorResponseDto<StoreResponseDto.SearchedStore> searchStore(@RequestParam(required = false) String storeName,
                                                                         @RequestParam(required = false) String region,
                                                                         @RequestParam(required = false) Long cursorId,
                                                                         @RequestParam(required = false, defaultValue = "10") Integer size,
                                                                         @RequestParam String sortType){
        return storeService.searchStore(storeName, region, cursorId, size, sortType);
    }
}
