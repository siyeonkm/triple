package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.TripRequestDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.Trip;
import com.interpark.triple.domain.repository.TripRepository;
import com.interpark.triple.domain.repository.TripperRepository;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TripperRepository tripperRepository;

    @Transactional(readOnly = true)
    public Trip findTrip(Long tripId) {
        return tripRepository.findByTripId(tripId).orElseThrow(
                () -> new CustomException(ErrorCode.TRIP_NOT_FOUND));
    }

    @Transactional
    public Trip addTrip(TripRequestDTO tripDTO) {
        Trip trip = Trip.builder()
                .title(tripDTO.getTitle())
                .city(tripDTO.getCity())
                .startDate(tripDTO.getStartDate())
                .endDate(tripDTO.getEndDate())
                .build();

        return tripRepository.save(trip);
    }

    @Transactional
    public void deleteTrip(Long tripId) {
        Trip trip = findTrip(tripId);
        try{
            tripRepository.delete(trip);
        }
        catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public Trip modifyTrip(Long tripId, TripRequestDTO tripDTO) {
        Trip trip = findTrip(tripId);
        trip.updateTrip(tripDTO.getTitle(), tripDTO.getCity(), tripDTO.getStartDate(), tripDTO.getEndDate());
        return tripRepository.save(trip);
    }

    @Transactional(readOnly = true)
    public Boolean CheckTripByCity(Long cityId) {
        return tripRepository.existsByCity_CityId(cityId);
    }

    @Transactional(readOnly = true)
    public List<City> findCitiesByTripsOngoing(Long memberId) {
        List<Trip> trips = tripperRepository.findOngoingTripsByMemberId(memberId, LocalDate.now());
        return sortCitiesByDate(makeUniqueTripList(trips));
    }

    @Transactional(readOnly = true)
    public List<City> findCitiesByTripsBooked(Long memberId) {
        List<Trip> trips = tripperRepository.findOngoingTripsByMemberId(memberId, LocalDate.now());
        return sortCitiesByDate(makeUniqueTripList(trips));
    }

    public List<Trip> makeUniqueTripList(List<Trip> trips) {
        Map<City, Trip> tripMap = new HashMap<>();
        for(Trip trip : trips) {
            tripMap.put(trip.getCity(), trip);
        }

        List<Trip> uniqueTrips = new ArrayList<>();
        tripMap.forEach((key, value) -> {
            uniqueTrips.add(value);
        });

        return uniqueTrips;
    }

    public List<City> sortCitiesByDate(List<Trip> uniqueTrips) {
        List<City> uniqueCities = new ArrayList<>();

        uniqueTrips.sort(new AscendingComparator());
        for(Trip trip : uniqueTrips) {
            uniqueCities.add(trip.getCity());
        }

        return uniqueCities;
    }

    static class AscendingComparator implements Comparator<Trip> {
        @Override
        public int compare(Trip t1, Trip t2) {
            if(t1.getStartDate().isBefore(t2.getStartDate())) return -1;
            else if(t1.getStartDate().isEqual(t2.getStartDate())) return 0;
            else return 1;
        }
    }
}
