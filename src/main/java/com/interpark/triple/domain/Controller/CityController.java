package com.interpark.triple.domain.Controller;

import com.interpark.triple.domain.Controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.Controller.DTO.CityResponseDTO;
import com.interpark.triple.domain.Service.CityService;
import com.interpark.triple.domain.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponseDTO cityAdd(@RequestBody CityRequestDTO cityDTO) {
        City city = cityService.addCity(cityDTO);
        return CityResponseDTO.builder().city(city).build();
    }
}
