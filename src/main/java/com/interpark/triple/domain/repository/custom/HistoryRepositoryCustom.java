package com.interpark.triple.domain.repository.custom;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import com.interpark.triple.domain.controller.DTO.CityDateTimeDTO;
import com.interpark.triple.domain.entity.History;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepositoryCustom {
    List<CityDateTimeDTO> findCitiesViewedByMemberId(Long memberId, LocalDateTime weekBefore);
}
