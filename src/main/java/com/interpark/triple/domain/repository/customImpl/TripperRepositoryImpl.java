package com.interpark.triple.domain.repository.customImpl;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
import com.interpark.triple.domain.entity.Trip;
import com.interpark.triple.domain.repository.custom.TripperRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.interpark.triple.domain.entity.QTrip.trip;
import static com.interpark.triple.domain.entity.QTripper.tripper;

@RequiredArgsConstructor
public class TripperRepositoryImpl implements TripperRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<CityDateDTO> findOngoingTripsByMemberId(Long memberId, LocalDate now) {
        return queryFactory
                .select(Projections.constructor(CityDateDTO.class, tripper.trip.startDate.as("date"), tripper.trip.city.as("city")))
                .from(tripper)
                .where(tripper.member.memberId.eq(memberId)
                        .and(tripper.trip.startDate.before(now)
                                .and(tripper.trip.endDate.after(now))
                        )
                ).orderBy(tripper.trip.startDate.asc())
                .fetch();
    }

    @Override
    public List<CityDateDTO> findFutureTripsByMemberId(Long memberId, LocalDate now) {
        List<Trip> trips = queryFactory
                .select(tripper.trip)
                .from(tripper)
                .where(tripper.member.memberId.eq(memberId).and(tripper.trip.startDate.after(now)))
                .groupBy(tripper.trip.tripId)
                .fetch();


        //여행들을 city로 group by하고 오름차순으로 정렬하기
        return queryFactory.select(Projections.constructor(
                        CityDateDTO.class, trip.startDate.as("date"), trip.city.as("city"))).distinct()
                .from(trip)
                .where(trip.in(trips))
                .orderBy(trip.startDate.asc())
                .fetch();
    }
}
