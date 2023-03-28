package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import com.interpark.triple.domain.controller.DTO.CityDateTimeDTO;
import com.interpark.triple.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<CityDateTimeDTO> findCitiesViewedByMemberId(Long memberId, LocalDateTime weekBefore);
}
