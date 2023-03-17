package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.Tripper;
import com.interpark.triple.domain.repository.custom.TripperRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TripperRepository extends JpaRepository<Tripper, Long>, TripperRepositoryCustom {

    @Override
    List<CityDateDTO> findOngoingTripsByMemberId(Long memberId, LocalDate now);

    @Override
    List<CityDateDTO> findFutureTripsByMemberId(Long memberId, LocalDate now);
}
