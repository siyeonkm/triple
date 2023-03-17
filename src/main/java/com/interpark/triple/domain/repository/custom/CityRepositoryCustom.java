package com.interpark.triple.domain.repository.custom;

import com.interpark.triple.domain.entity.City;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepositoryCustom {
    List<City> findCitiesByRandom(List<Long> cities);
}
