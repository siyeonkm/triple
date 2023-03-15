package com.interpark.triple.Controller.DTO;

import com.interpark.triple.domain.City;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityResponseDTO {
    private Long id;
    private String name;

    @Builder
    public CityResponseDTO(City city) {
        this.name = city.getName();
    }
}
