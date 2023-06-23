package com.example.pharmacyRecommendation.api.service

import spock.lang.Specification

import java.nio.charset.StandardCharsets

class KakaoUrlBuilderServiceTest extends Specification {

    private KakaoUrlBuilderService kakaoUrlBuilderService

    def setup() {
        kakaoUrlBuilderService = new KakaoUrlBuilderService()
    }

    def "builderUriByAddressSearch - 한글 파라미터의 경우 정상적인 인코딩"() {
        given:
        String address = "서울 동작구"
        def charset = StandardCharsets.UTF_8

        when:
        def uri = kakaoUrlBuilderService.builderByAddressSearch(address)
        def decodedResult = URLDecoder.decode(uri.toASCIIString(), charset)

        then:
        decodedResult == "https://dapi.kakao.com/v2/local/search/address.json?query=서울 동작구"
    }

    
}
