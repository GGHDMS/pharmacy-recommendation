package com.example.pharmacyRecommendation.pharmacy.service;

import com.example.pharmacyRecommendation.api.dto.KakaoApiResponseDto;
import com.example.pharmacyRecommendation.api.service.KakaoAddressSearchService;
import com.example.pharmacyRecommendation.direction.entity.Direction;
import com.example.pharmacyRecommendation.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendPharmacyList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address: {}", address);
            return;
        }

        List<Direction> directionList = directionService.buildDirectionList(kakaoApiResponseDto.getDocumentList().get(0));

        directionService.saveAll(directionList);
    }
}