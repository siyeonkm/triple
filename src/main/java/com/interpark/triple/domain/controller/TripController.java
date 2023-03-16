package com.interpark.triple.domain.controller;

import com.interpark.triple.domain.controller.DTO.TripRequestDTO;
import com.interpark.triple.domain.controller.DTO.TripResponseDTO;
import com.interpark.triple.domain.Service.TripService;
import com.interpark.triple.domain.entity.Trip;
import com.interpark.triple.global.error.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> tripDetail(@PathVariable Long tripId) {
        Trip trip = tripService.findTrip(tripId);
        TripResponseDTO tripResponseDTO = TripResponseDTO.builder().trip(trip).build();
        return ResponseEntity.ok(tripResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<TripResponseDTO> tripAdd(@RequestBody TripRequestDTO tripDTO) {
        Trip trip = tripService.addTrip(tripDTO);
        TripResponseDTO tripResponseDTO = TripResponseDTO.builder().trip(trip).build();
        return ResponseEntity.created(URI.create("/trips/" + trip.getTripId())).body(tripResponseDTO);
    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> tripModify(@PathVariable Long tripId, @RequestBody TripRequestDTO tripDTO) {
        Trip tripModified = tripService.modifyTrip(tripId, tripDTO);
        TripResponseDTO tripResponseDTO = TripResponseDTO.builder().trip(tripModified).build();
        return ResponseEntity.ok(tripResponseDTO);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<SuccessCode> tripDelete(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok(SuccessCode.DELETE_TRIP_SUCCESS);
    }
}
