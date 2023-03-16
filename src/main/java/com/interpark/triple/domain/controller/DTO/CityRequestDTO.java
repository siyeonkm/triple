package com.interpark.triple.domain.controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CityRequestDTO {
    private String name;

    private List<String> places;

    @Builder
    public CityRequestDTO(String name, List<String> places) {
        this.name = name;
        this.places = places;
    }
}
