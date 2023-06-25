package com.example.pharmacyRecommendation.api.service;

import com.example.pharmacyRecommendation.api.dto.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoCategorySearchService {

    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;

    private static final String PHARMACY_CATEGORY = "PM9";

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;


    public KakaoApiResponseDto requestPharmacyCategorySearch(double latitude, double longitude, double radius) {
        URI uri = kakaoUriBuilderService.builderByCategorySearch(latitude, longitude, radius, PHARMACY_CATEGORY);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        // kakao api 호출
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
    }
}
