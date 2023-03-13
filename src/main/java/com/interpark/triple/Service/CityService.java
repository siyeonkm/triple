package com.interpark.triple.Service;

import com.interpark.triple.Controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.City;
import com.interpark.triple.repository.CityRepository;
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
}
