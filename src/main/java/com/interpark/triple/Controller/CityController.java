package com.interpark.triple.Controller;

import com.interpark.triple.Controller.DTO.CityRequestDTO;
import com.interpark.triple.Controller.DTO.CityResponseDTO;
import com.interpark.triple.Service.CityService;
import com.interpark.triple.domain.City;
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
