package com.interpark.triple.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interpark.triple.domain.controller.DTO.CityRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CityControllerTest extends BaseApiTest {

    @Test
    void cityDetails() throws Exception {

        //when
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/cities/1"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(equalTo(1)))
                .andExpect(jsonPath("$.name").value(equalTo("삿포로")))
                .andDo(print());
    }

    @Test
    void cityListByMember() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/cities/")
                .header("memberId", 1L));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(equalTo(5)))
                .andExpect(jsonPath("$[0].name").value(equalTo("부산")))
                .andDo(print());
    }

    @Test
    void cityAdd() throws Exception {
        //given
        CityRequestDTO cityRequestDTO = CityRequestDTO.builder().name("상하이").build();
        //when
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/cities/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityRequestDTO))
        );
        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("name").value("상하이")
                );
    }

    @Test
    void cityModify() throws Exception {
        //given
        CityRequestDTO cityRequestDTO = CityRequestDTO.builder().name("뉴욕").build();

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/cities/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalTo("뉴욕")))
                .andDo(print());
    }

    @Test
    void cityDelete() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.delete("/cities/1")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}