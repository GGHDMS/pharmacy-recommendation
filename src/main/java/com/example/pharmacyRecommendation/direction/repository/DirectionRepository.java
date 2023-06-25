package com.example.pharmacyRecommendation.direction.repository;

import com.example.pharmacyRecommendation.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
