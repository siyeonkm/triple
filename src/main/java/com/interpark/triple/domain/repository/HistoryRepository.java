package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select h from History h " +
            "where h.member.memberId = :memberId " +
            "and h.viewedDate < :weekBefore")
    List<History> findCitiesViewedByMemberId(Long memberId, LocalDateTime weekBefore);
}
