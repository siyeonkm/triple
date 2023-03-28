package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.entity.City;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {
    @Autowired
    CityRepository cityRepository;

    @Test
    void findCitiesByRandom() {
        List<Long> cityIds = new ArrayList<>(List.of(1L, 3L, 5L));
        List<City> cities = cityRepository.findCitiesByRandom(cityIds, 3);

        for(City city : cities) {
            System.out.println("id = " + city.getCityId() + "   city = " + city.getName());
            Assertions.assertThat(city.getCityId()).isNotEqualTo(1L).isNotEqualTo(3L).isNotEqualTo(5L);
        }
    }
}