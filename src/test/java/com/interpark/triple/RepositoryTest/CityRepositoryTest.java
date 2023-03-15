package com.interpark.triple.RepositoryTest;

import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.repository.CityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    void getCityTest() {
        City city = cityRepository.findByName("삿포로");
        Assertions.assertThat(city.getCityId()).isEqualTo(1L);
    }
}
