package com.interpark.triple.APITest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interpark.triple.Controller.DTO.CityRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("도시 관련 api 테스트")
public class CityApiTest extends BaseApiTest{

    @Test
    void cityAddTest() throws Exception {
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
}
