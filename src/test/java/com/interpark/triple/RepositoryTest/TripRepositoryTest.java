package com.interpark.triple.RepositoryTest;

import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.repository.TripperRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import com.interpark.triple.domain.repository.TripRepository;

@WebMvcTest
public class TripRepositoryTest {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    TripperRepository tripperRepository;

    @Test
    void getCityTest() {
        City city = cityRepository.findByName("삿포로").get();
        Assertions.assertThat(city.getCityId()).isEqualTo(1L);
    }
}
