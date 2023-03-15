package com.interpark.triple.domain.controller;

import com.interpark.triple.domain.controller.DTO.CityResponseDTO;
import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.service.CityService;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.global.error.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping("/{cityId}")
    public ResponseEntity<CityResponseDTO> cityDetails(@PathVariable Long cityId) {
        City city = cityService.findCity(cityId);
        CityResponseDTO cityResponseDTO = CityResponseDTO.builder().city(city).build();
        return ResponseEntity.ok(cityResponseDTO);
    }

    //TODO: 로직 완성하기
    @GetMapping()
    public ResponseEntity<List<CityResponseDTO>> cityList(HttpServletRequest request) {
        Long memberId = Long.valueOf(request.getHeader("memberId"));
        List<CityResponseDTO> dtoList = new ArrayList<>();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping()
    public ResponseEntity<CityResponseDTO> cityAdd(@RequestBody CityRequestDTO cityDTO) {
        City city = cityService.addCity(cityDTO);
        CityResponseDTO cityResponseDTO = CityResponseDTO.builder().city(city).build();
        return ResponseEntity.ok(cityResponseDTO);
    }

    @PatchMapping("/{cityId}")
    public ResponseEntity<CityResponseDTO> cityModify(@PathVariable Long cityId, @RequestBody CityRequestDTO cityDTO) {
        City city = cityService.findCity(cityId);
        City cityPatched = cityService.modifyCity(city, cityDTO);
        CityResponseDTO cityResponseDTO = CityResponseDTO.builder().city(cityPatched).build();
        return ResponseEntity.ok(cityResponseDTO);
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<SuccessCode> cityDelete(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return ResponseEntity.ok(SuccessCode.DELETE_CITY_SUCCESS);
    }
}
