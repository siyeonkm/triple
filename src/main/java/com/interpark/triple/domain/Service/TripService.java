package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.TripRequestDTO;
import com.interpark.triple.domain.entity.Trip;
import com.interpark.triple.domain.repository.TripRepository;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    public Trip findTrip(Long tripId) {
        return tripRepository.findByTripId(tripId).orElseThrow(
                () -> new CustomException(ErrorCode.TRIP_NOT_FOUND));
    }

    public Trip addTrip(TripRequestDTO tripDTO) {
        Trip trip = Trip.builder()
                .title(tripDTO.getTitle())
                .city(tripDTO.getCity())
                .startDate(tripDTO.getStartDate())
                .endDate(tripDTO.getEndDate())
                .build();

        return tripRepository.save(trip);
    }

    public void deleteTrip(Long tripId) {
        Trip trip = findTrip(tripId);
        try{
            tripRepository.delete(trip);
        }
        catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    public Trip modifyTrip(Long tripId, TripRequestDTO tripDTO) {
        Trip trip = findTrip(tripId);
        trip.updateTrip(tripDTO.getTitle(), tripDTO.getCity(), tripDTO.getStartDate(), tripDTO.getEndDate());
        return tripRepository.save(trip);
    }

    public Boolean checkTripbyCity(Long cityId) {
        return tripRepository.existsByCity_CityId(cityId);
    }
}
