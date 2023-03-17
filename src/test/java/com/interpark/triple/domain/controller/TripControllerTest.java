package com.interpark.triple.domain.controller;

import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import com.interpark.triple.domain.controller.DTO.TripRequestDTO;
import com.interpark.triple.domain.entity.City;
import com.interpark.triple.domain.repository.CityRepository;
import com.interpark.triple.domain.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class TripControllerTest extends BaseApiTest {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TripRepository tripRepository;

    @Test
    void tripDetail() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/trips/1"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(equalTo("일본여행")))
                .andDo(print());
    }

    @Test
    void tripAdd() throws Exception {
        City city = cityRepository.findByName("프랑스");
        LocalDate start = LocalDate.now().minusDays(3L);
        LocalDate end = LocalDate.now().plusDays(10L);

        //given
        TripRequestDTO tripRequestDTO = TripRequestDTO.builder()
                .title("프랑스좋아")
                .city(city).startDate(start).endDate(end).build();
        //when
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/cities/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripRequestDTO))
        );
        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(equalTo("프랑스좋아"))
                );
    }

    @Test
    void tripModify() throws Exception{
        City city = cityRepository.findByName("이탈리아");
        LocalDate start = LocalDate.now().minusDays(3L);
        LocalDate end = LocalDate.now().plusDays(10L);

        //given
        TripRequestDTO tripRequestDTO = TripRequestDTO.builder()
                .title("이탈리아!")
                .city(city).build();

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/trips/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(equalTo("이탈리아!")))
                .andDo(print());
    }

    @Test
    void tripDelete() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.delete("/trips/1")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}