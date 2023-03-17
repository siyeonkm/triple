package com.interpark.triple.domain.controller;

import com.interpark.triple.domain.controller.DTO.CityResponseDTO;
import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.service.CityService;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.service.TripService;
import com.interpark.triple.global.error.CustomException;
import com.interpark.triple.global.error.ErrorCode;
import com.interpark.triple.global.error.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final TripService tripService;

    @GetMapping("/{cityId}")
    public ResponseEntity<CityResponseDTO> cityDetails(@PathVariable Long cityId) {
        City city = cityService.findCity(cityId);
        CityResponseDTO cityResponseDTO = CityResponseDTO.builder().city(city).build();
        return ResponseEntity.ok(cityResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<CityResponseDTO>> cityListByMember(HttpServletRequest request) {
        Long memberId = Long.valueOf(request.getHeader("memberId"));

        List<CityResponseDTO> ongoingTrip = cityToDTO(tripService.findCitiesByTripsOngoing(memberId));
        List<CityResponseDTO> cityDTOList = new ArrayList<>(ongoingTrip);

        List<City> futureTrip = tripService.findCitiesByTripsBooked(memberId);
        List<CityResponseDTO> cityRecommendList = cityToDTO(cityService.recommendCities(memberId, futureTrip));
        cityDTOList.addAll(cityRecommendList);

        return ResponseEntity.ok(cityDTOList);
    }

    @PostMapping()
    public ResponseEntity<CityResponseDTO> cityAdd(@RequestBody CityRequestDTO cityDTO) {
        City city = cityService.addCity(cityDTO);
        CityResponseDTO cityResponseDTO = CityResponseDTO.builder().city(city).build();
        return ResponseEntity.created(URI.create("/cities/" + cityResponseDTO.getId())).body(cityResponseDTO);
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
        if(!tripService.CheckTripByCity(cityId)){
            cityService.deleteCity(cityId);
        }
        else {
            throw new CustomException(ErrorCode.DELETE_CITY_FAILED);
        }
        return ResponseEntity.ok(SuccessCode.DELETE_CITY_SUCCESS);
    }

    public List<CityResponseDTO> cityToDTO(List<City> cityList) {
        List<CityResponseDTO> cityResponseDTOList = new ArrayList<>();
        for(City city : cityList) {
            cityResponseDTOList.add(CityResponseDTO.builder().city(city).build());
        }
        return cityResponseDTOList;
    }
}
