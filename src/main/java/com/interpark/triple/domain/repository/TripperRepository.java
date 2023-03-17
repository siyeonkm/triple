package com.interpark.triple.domain.repository;

import com.interpark.triple.domain.entity.Trip;
import com.interpark.triple.domain.entity.Tripper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TripperRepository extends JpaRepository<Tripper, Long> {
    @Query("select t.trip from Tripper t " +
            "where t.member.memberId = :memberId " +
            "and t.trip.startDate < :now " +
            "and t.trip.endDate > :now ")
    List<Trip> findOngoingTripsByMemberId(Long memberId, LocalDate now);

    @Query("select t.trip from Tripper t " +
            "where t.member.memberId = :memberId " +
            "and t.trip.startDate > :now ")
    List<Trip> findFutureTripsByMemberId(Long memberId, LocalDate now);
}
