package com.interpark.triple.domain.controller.DTO;

import com.interpark.triple.domain.entity.City;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CityDateDTO {
    private LocalDate date;
    private City city;

    @QueryProjection
    public CityDateDTO(LocalDate date, City city) {
        this.date = date;
        this.city = city;
    }
}
