package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.repository.CityRepository;
import com.interpark.triple.domain.repository.HistoryRepository;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final HistoryRepository historyRepository;

    public City addCity(CityRequestDTO cityDTO) {
        City city = City.builder().name(cityDTO.getName()).build();
        return cityRepository.save(city);
    }

    public City findCity(Long cityId) {
        return cityRepository.findByCityId(cityId)
                .orElseThrow(() -> new CustomException(ErrorCode.CITY_NOT_FOUND));
    }

    public City modifyCity(City city, CityRequestDTO cityDTO) {
        city.updateNameandPlaces(cityDTO.getName(), cityDTO.getPlaces());
        return cityRepository.save(city);
    }

    public void deleteCity(Long cityId) {
        City city = findCity(cityId);
        try {
            cityRepository.delete(city);
        }
        catch(Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: 일주일 이내에 한번 조회된 도시
    //TODO: 나머지는 무작위(필요한 양을 파라미터로 넘기자)
    public List<City> findCityList(Long memberId, List<City> futureTrip) {
        int count = 0;

    }
}
