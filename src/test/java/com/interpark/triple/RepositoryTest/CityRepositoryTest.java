package com.interpark.triple.RepositoryTest;

import com.interpark.triple.domain.City;
import com.interpark.triple.repository.CityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    void getCityTest() {
        City city = cityRepository.findByCityId(1L);
        Long cityId = city.getCityId();
        Assertions.assertThat(cityId).isEqualTo(1L);
    }
}
