package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByCityId(Long id);

    City findByName(String name);

    List<City> findCitiesByCreatedDateAfter(LocalDateTime yesterday);

    List<City> findCitiesByRandom(List<Long> cities);


}
