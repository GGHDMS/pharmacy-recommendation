package com.example.pharmacyRecommendation.pharmacy.repository;

import com.example.pharmacyRecommendation.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
