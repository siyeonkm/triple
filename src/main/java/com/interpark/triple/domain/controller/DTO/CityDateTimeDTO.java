package com.interpark.triple.domain.controller.DTO;

import com.interpark.triple.domain.entity.City;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CityDateTimeDTO {
    private LocalDateTime date;
    private City city;

    @QueryProjection
    public CityDateTimeDTO(LocalDateTime date, City city) {
        this.date = date;
        this.city = city;
    }
}
