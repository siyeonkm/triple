package com.interpark.triple.domain.controller.DTO;

import com.interpark.triple.domain.entity.City;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CityResponseDTO {
    private Long id;
    private String name;

    private List<String> places;

    @Builder
    public CityResponseDTO(City city) {
        this.id = city.getCityId();
        this.name = city.getName();
        this.places = city.getPlaces();
    }
}
