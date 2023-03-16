package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityId(Long id);

    City findByName(String name);
}
