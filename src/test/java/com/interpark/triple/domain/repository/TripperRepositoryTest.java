package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TripperRepositoryTest {
    @Autowired
    TripperRepository tripperRepository;

    @Test
    void findOngoingTripsByMemberId() {
        List<CityDateDTO> cities = tripperRepository.findOngoingTripsByMemberId(1L, LocalDate.now());
        for(CityDateDTO cityDateDTO : cities) {
            System.out.println("city = " + cityDateDTO.getCity().getName() + "   date = " + cityDateDTO.getDate());
            Assertions.assertThat(cityDateDTO.getDate()).isBefore(LocalDate.now());
        }
    }

    @Test
    void findFutureTripsByMemberId() {
        List<CityDateDTO> cities = tripperRepository.findFutureTripsByMemberId(1L, LocalDate.now());
        for(CityDateDTO cityDateDTO : cities) {
            System.out.println("city = " + cityDateDTO.getCity().getName() + "   date = " + cityDateDTO.getDate());
            Assertions.assertThat(cityDateDTO.getDate()).isAfter(LocalDate.now());
        }
    }
}