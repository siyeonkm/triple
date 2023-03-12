package com.interpark.triple.RepositoryTest;

import com.interpark.triple.domain.City;
import com.interpark.triple.repository.CityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
