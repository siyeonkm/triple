package com.interpark.triple.domain.controller.DTO;

import com.interpark.triple.domain.entity.City;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class TripRequestDTO {
    private String title;
    private City city;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public TripRequestDTO(String title, City city, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
