package kr.co.contxt.commonapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.contxt.commonapi.dto.SensorNameDto;
import kr.co.contxt.commonapi.dto.TimeIntervalRequest;
import kr.co.contxt.commonapi.dto.TimeIntervalResponse;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.service.TimeIntervalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TimeIntervalRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TimeIntervalService timeIntervalService;

    @Test
    void getTimeIntervalBySensor() throws Exception {
        long sensorId = 1L;
        String sensorName = "test sensor";
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String beginAsString = begin.format(formatter);
        String endAsString = end.format(formatter);
        TimeIntervalResponse timeIntervalResponse = TimeIntervalResponse.builder()
                .sensorName(sensorName)
                .begin(begin)
                .end(end)
                .build();

        given(timeIntervalService.getTimeInterval(anyLong())).willReturn(timeIntervalResponse);

        mockMvc.perform(get("/api/common/time-interval/" + sensorId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensorName", equalTo(sensorName)))
                .andExpect(jsonPath("$.begin", equalTo(beginAsString)))
                .andExpect(jsonPath("$.end", equalTo(endAsString)));
    }

    @Test
    void getTimeIntervalBySensorException() throws Exception {
        long sensorId = 1L;

        given(timeIntervalService.getTimeInterval(anyLong()))
                .willThrow(new SensorNotFoundException("탐지 시간을 찾을 수 없습니다."));

        mockMvc.perform(get("/api/common/time-interval/" + sensorId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("탐지 시간을 찾을 수 없습니다.")));
    }

    @Test
    void getTimeIntervalBySensorName() throws Exception {
        String sensorName = "test sensor";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String beginAsString = begin.format(formatter);
        String endAsString = end.format(formatter);
        SensorNameDto sensorNameDto = new SensorNameDto(sensorName);
        TimeIntervalResponse timeIntervalResponse = TimeIntervalResponse.builder()
                .sensorName(sensorName)
                .begin(begin)
                .end(end)
                .build();

        given(timeIntervalService.getTimeInterval(sensorNameDto)).willReturn(timeIntervalResponse);

        mockMvc.perform(get("/api/common/time-interval")
                        .param("sensorName", sensorName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensorName", equalTo(sensorName)))
                .andExpect(jsonPath("$.begin", equalTo(beginAsString)))
                .andExpect(jsonPath("$.end", equalTo(endAsString)));
    }

    @Test
    void getTimeIntervalBySensorNameException() throws Exception {
        String sensorName = "test sensor";
        SensorNameDto sensorNameDto = new SensorNameDto(sensorName);

        given(timeIntervalService.getTimeInterval(sensorNameDto))
                .willThrow(new SensorNotFoundException("탐지 시간을 찾을 수 없습니다."));

        mockMvc.perform(get("/api/common/time-interval")
                        .param("sensorName", sensorName))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("탐지 시간을 찾을 수 없습니다.")));
    }

    @Test
    void addTimeInterval() throws Exception {
        Long sensorId = 1L;
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        TimeIntervalRequest timeIntervalRequest = new TimeIntervalRequest(sensorId, begin, end);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(post("/api/common/time-interval")
                        .content(objectMapper.writeValueAsString(timeIntervalRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(timeIntervalService, times(1)).createTimeInterval(timeIntervalRequest);
    }

    @Test
    void updateTimeInterval() throws Exception {
        String sensorName = "test sensor";
        long timeIntervalId = 1L;
        Long sensorId = 1L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String beginAsString = begin.format(formatter);
        String endAsString = end.format(formatter);
        TimeIntervalRequest timeIntervalRequest = new TimeIntervalRequest(sensorId, begin, end);
        TimeIntervalResponse timeIntervalResponse = TimeIntervalResponse.builder()
                .sensorName(sensorName)
                .begin(begin)
                .end(end)
                .build();

        given(timeIntervalService.updateTimeInterval(anyLong(), any())).willReturn(timeIntervalResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(put("/api/common/time-interval/" + timeIntervalId)
                        .content(objectMapper.writeValueAsString(timeIntervalRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensorName", equalTo(sensorName)))
                .andExpect(jsonPath("$.begin", equalTo(beginAsString)))
                .andExpect(jsonPath("$.end", equalTo(endAsString)));
    }

    @Test
    void updateTimeIntervalException() throws Exception {
        long timeIntervalId = 1L;
        Long sensorId = 1L;
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        TimeIntervalRequest timeIntervalRequest = new TimeIntervalRequest(sensorId, begin, end);

        given(timeIntervalService.updateTimeInterval(anyLong(), any())).willThrow(new SensorNotFoundException("탐지 시간을 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(put("/api/common/time-interval/" + timeIntervalId)
                        .content(objectMapper.writeValueAsString(timeIntervalRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("탐지 시간을 찾을 수 없습니다.")));
    }
}