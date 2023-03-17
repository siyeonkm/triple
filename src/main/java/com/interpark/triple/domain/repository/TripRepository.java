package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findByTripId(Long id);
    Boolean existsByCity_CityId(Long cityId);
}
