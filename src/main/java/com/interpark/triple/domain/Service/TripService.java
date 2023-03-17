package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.CityDateDTO;
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
        try {
            return tripRepository.findByTripId(tripId);
        }
        catch(Exception e) {
            throw new CustomException(ErrorCode.TRIP_NOT_FOUND);
        }
    }

    @Transactional
    public List<City> findCitiesByTripsOngoing(Long memberId) {
        //중복허용, 정렬완료
        List<CityDateDTO> trips = tripperRepository.findOngoingTripsByMemberId(memberId, LocalDate.now());

        List<City> cities = new ArrayList<>();
        for(CityDateDTO cityDateDTO : trips) {
            cities.add(cityDateDTO.getCity());
        }
        return cities;
    }

    @Transactional
    public List<City> findCitiesByTripsBooked(Long memberId) {
        //중복제거, 정렬 미완료
        List<CityDateDTO> trips = tripperRepository.findFutureTripsByMemberId(memberId, LocalDate.now());
        return sortCitiesByDate(trips);
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

    public List<City> sortCitiesByDate(List<CityDateDTO> trips) {
        List<City> sortedCities = new ArrayList<>();

        trips.sort(new AscendingComparator());
        for(CityDateDTO cityDateDTO : trips) {
            sortedCities.add(cityDateDTO.getCity());
        }
        return sortedCities;
    }

    public boolean isEndDateFuture(LocalDate endDate) {
        return endDate.isAfter(LocalDate.now());
    }

    static class AscendingComparator implements Comparator<CityDateDTO> {
        @Override
        public int compare(CityDateDTO c1, CityDateDTO c2) {
            if(c1.getDate().isBefore(c2.getDate())) return -1;
            else if(c1.getDate().isEqual(c2.getDate())) return 0;
            else return 1;
        }
    }
}
