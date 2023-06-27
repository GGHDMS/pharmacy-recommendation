package com.example.pharmacyRecommendation.pharmacy.cache

import com.example.pharmacyRecommendation.AbstractIntegrationContainerBaseTest
import com.example.pharmacyRecommendation.pharmacy.dto.PharmacyDto
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRedisTemplateServiceTest extends AbstractIntegrationContainerBaseTest  {

    @Autowired
    private PharmacyRedisTemplateService pharmacyRedisTemplateService

    def setup() {
        pharmacyRedisTemplateService.findAll()
        .forEach(dto ->{
            pharmacyRedisTemplateService.delete(dto.getId())
        })
    }

    def "save success"() {
        given:
        String pharmacyName = "name"
        String pharmacyAddress = "address"
        def dto = PharmacyDto.builder()
                .id(1L)
                .pharmacyName(pharmacyName)
                .pharmacyAddress(pharmacyAddress)
                .build()

        when:
        pharmacyRedisTemplateService.save(dto)
        def result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 1
        result.get(0).id == 1L
        result.get(0).pharmacyName == pharmacyName
        result.get(0).pharmacyAddress == pharmacyAddress
    }

    def "save fail"() {
        given:
        def dto = PharmacyDto.builder()
                .build()

        when:
        pharmacyRedisTemplateService.save(dto)
        def result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 0
    }

    def "delete success"() {
        given:
        String pharmacyName = "name"
        String pharmacyAddress = "address"
        def dto = PharmacyDto.builder()
                .id(1L)
                .pharmacyName(pharmacyName)
                .pharmacyAddress(pharmacyAddress)
                .build()

        when:
        pharmacyRedisTemplateService.save(dto)
        pharmacyRedisTemplateService.delete(dto.getId())
        def result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 0
    }

}
