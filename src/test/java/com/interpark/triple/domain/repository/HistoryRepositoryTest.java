package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.controller.DTO.CityDateTimeDTO;
import com.interpark.triple.domain.entity.City;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HistoryRepositoryTest {
    @Autowired
    HistoryRepository historyRepository;

    @Test
    void findCitiesViewedByMemberId() {
        List<CityDateTimeDTO> cities = historyRepository.findCitiesViewedByMemberId(1L, LocalDateTime.now().minusDays(7L));
        for(CityDateTimeDTO cityDateTimeDTO : cities) {
            System.out.println("city = " + cityDateTimeDTO.getCity().getName() + "   date = " + cityDateTimeDTO.getDate());
            Assertions.assertThat(cityDateTimeDTO.getDate()).isAfter(LocalDateTime.now().minusDays(7L));
        }
    }
}