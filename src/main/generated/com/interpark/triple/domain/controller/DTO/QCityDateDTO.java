package com.interpark.triple.domain.controller.DTO;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.interpark.triple.domain.controller.DTO.QCityDateDTO is a Querydsl Projection type for CityDateDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCityDateDTO extends ConstructorExpression<CityDateDTO> {

    private static final long serialVersionUID = 471719124L;

    public QCityDateDTO(com.querydsl.core.types.Expression<java.time.LocalDate> date, com.querydsl.core.types.Expression<? extends com.interpark.triple.domain.entity.City> city) {
        super(CityDateDTO.class, new Class<?>[]{java.time.LocalDate.class, com.interpark.triple.domain.entity.City.class}, date, city);
    }

}

