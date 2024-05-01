package kr.co.contxt.commonapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.service.SensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SensorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SensorService sensorService;

    @Test
    void getSensorList() throws Exception {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        SensorResponse sensorResponse = SensorResponse.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorService.getAllSensors()).willReturn(List.of(sensorResponse));

        // when
        // then
        mockMvc.perform(get("/api/common/sensor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$[0].sensorName", equalTo(sensorName)));
    }

    @Test
    void getSensor() throws Exception {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        SensorResponse sensorResponse = SensorResponse.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorService.getSensor(anyLong())).willReturn(sensorResponse);

        // when
        // then
        mockMvc.perform(get("/api/common/sensor/" + sensorId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.sensorName", equalTo(sensorName)));
    }

    @Test
    void getSensorException() throws Exception {
        long sensorId = 1L;

        given(sensorService.getSensor(anyLong()))
                .willThrow(new SensorNotFoundException("Sensor를 찾을 수 없습니다."));

        mockMvc.perform(get("/api/common/sensor/" + sensorId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Sensor를 찾을 수 없습니다.")));
    }

    @Test
    void addSensor() throws Exception {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorService.saveSensor(any())).willReturn(sensor);

        // when
        // then
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/common/sensor")
                        .content(objectMapper.writeValueAsString(sensor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.sensorName", equalTo(sensorName)));
    }

    @Test
    void updateSensor() throws Exception {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorService.updateSensor(anyLong(), any())).willReturn(sensor);

        // when
        // then
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/api/common/sensor/" + sensorId)
                        .content(objectMapper.writeValueAsString(sensor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.sensorName", equalTo(sensorName)));
    }

    @Test
    void updateSensorException() throws Exception {
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorService.updateSensor(anyLong(), any()))
                .willThrow(new SensorNotFoundException("Sensor를 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/api/common/sensor/" + sensorId)
                        .content(objectMapper.writeValueAsString(sensor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Sensor를 찾을 수 없습니다.")));
    }

}