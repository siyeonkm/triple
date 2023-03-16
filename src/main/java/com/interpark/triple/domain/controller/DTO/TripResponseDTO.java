package com.interpark.triple.domain.controller.DTO;

import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.Trip;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TripResponseDTO {
    private Long id;
    private String title;
    private City city;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public TripResponseDTO(Trip trip) {
        this.id =trip.getTripId();
        this.title = trip.getTitle();
        this.city = trip.getCity();
        this.startDate =trip.getStartDate();
        this.endDate = trip.getEndDate();
    }
}
