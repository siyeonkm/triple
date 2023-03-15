package com.interpark.triple.domain.service;

import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.repository.CityRepository;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

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

    //TODO: 도시에 지정된 여행이 없을 경우에만 삭제 가능
    public void deleteCity(Long cityId) {
        City city = findCity(cityId);
        try {
            cityRepository.delete(city);
        }
        catch(Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
