package com.interpark.triple.domain.controller.DTO;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.interpark.triple.domain.controller.DTO.QCityDateTimeDTO is a Querydsl Projection type for CityDateTimeDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCityDateTimeDTO extends ConstructorExpression<CityDateTimeDTO> {

    private static final long serialVersionUID = 1636466759L;

    public QCityDateTimeDTO(com.querydsl.core.types.Expression<java.time.LocalDateTime> date, com.querydsl.core.types.Expression<? extends com.interpark.triple.domain.entity.City> city) {
        super(CityDateTimeDTO.class, new Class<?>[]{java.time.LocalDateTime.class, com.interpark.triple.domain.entity.City.class}, date, city);
    }

}

