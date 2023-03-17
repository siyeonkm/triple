package com.interpark.triple.domain.repository.custom;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;

import java.time.LocalDate;
import java.util.List;

public interface TripperRepositoryCustom {
    List<CityDateDTO> findOngoingTripsByMemberId(Long memberId, LocalDate now);
    List<CityDateDTO> findFutureTripsByMemberId(Long memberId, LocalDate now);
}
