package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.dto.WeatherResponseDto;
import kr.co.contxt.commonapi.exception.SkyInfoNotFoundException;
import kr.co.contxt.commonapi.exception.TemperatureInfoNotFoundException;
import kr.co.contxt.commonapi.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class WeatherRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherService weatherService;


    @Test
    void getWeather() throws Exception {
        // given
        Float temperature = 21.0f;
        String sky = "맑음";
        WeatherResponseDto weatherResponseDto = new WeatherResponseDto(temperature, sky);

        given(weatherService.getWeather()).willReturn(weatherResponseDto);

        //when
        //then
        mockMvc.perform(get("/api/common/weather"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.temperature", equalTo(temperature.doubleValue())))
                .andExpect(jsonPath("$.sky", equalTo(sky)));
    }

    @Test
    void getWeatherTemperatureInfoNotFoundException() throws Exception {
        given(weatherService.getWeather())
                .willThrow(new TemperatureInfoNotFoundException("온도를 가져올 수 없습니다."));

        mockMvc.perform(get("/api/common/weather"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("온도를 가져올 수 없습니다.")));
    }

    @Test
    void getWeatherSkyInfoNotFoundException() throws Exception {
        given(weatherService.getWeather())
                .willThrow(new SkyInfoNotFoundException("날씨를 가져올 수 없습니다."));

        mockMvc.perform(get("/api/common/weather"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("날씨를 가져올 수 없습니다.")));
    }
}