package com.interpark.triple.domain.Controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityRequestDTO {
    private String name;

    @Builder
    public CityRequestDTO(String name) {
        this.name = name;
    }
}
