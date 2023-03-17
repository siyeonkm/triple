package com.interpark.triple.domain.repository.customImpl;

import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.entity.QCity;
import com.interpark.triple.domain.repository.custom.CityRepositoryCustom;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.interpark.triple.domain.entity.QCity.city;

@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<City> findCitiesByRandom(List<Long> cities) {
        return jpaQueryFactory
                .select(city)
                .from(city)
                .where(city.cityId.notIn(cities))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(10)
                .fetch();
    }
}
