package com.interpark.triple.domain.repository.customImpl;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import com.interpark.triple.domain.controller.DTO.CityDateTimeDTO;
import com.interpark.triple.domain.entity.History;
import com.interpark.triple.domain.entity.QHistory;
import com.interpark.triple.domain.repository.custom.HistoryRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.interpark.triple.domain.entity.QHistory.history;
import static com.interpark.triple.domain.entity.QTripper.tripper;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CityDateTimeDTO> findCitiesViewedByMemberId(Long memberId, LocalDateTime weekBefore) {
        return queryFactory
                .select(Projections.constructor(CityDateTimeDTO.class, history.viewedDate.as("date"), history.city.as("city")))
                .from(history)
                .where(history.member.memberId.eq(memberId).and(history.viewedDate.after(weekBefore)))
                .orderBy(history.viewedDate.asc())
                .fetch();
    }
}
