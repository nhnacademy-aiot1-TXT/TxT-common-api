package kr.co.contxt.commonapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DeviceSensorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DeviceSensorService deviceSensorService;

    @Test
    void getSensorListByDevice() throws Exception {
        // given
        long deviceId = 1L;
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorListByDevice(anyLong())).willReturn(List.of(deviceSensorResponse));

        // when
        // then
        mockMvc.perform(get("/api/common/device-sensor/" + deviceId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$[0].offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorListByDeviceName() throws Exception {
        String deviceName = "test device";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceNameDto deviceNameDto = new DeviceNameDto(deviceName);
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorListByDevice(deviceNameDto)).willReturn(List.of(deviceSensorResponse));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(get("/api/common/device-sensor/sensors")
                        .param("deviceName", deviceName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$[0].offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorByDeviceAndSensor() throws Exception {
        // given
        long deviceId = 1L;
        long sensorId = 1L;
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorByDeviceAndSensor(anyLong(), anyLong())).willReturn(deviceSensorResponse);

        mockMvc.perform(get("/api/common/device-sensor/" + deviceId + "/" + sensorId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$.offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorByDeviceAndSensorException() throws Exception {
        long deviceId = 1L;
        long sensorId = 1L;

        given(deviceSensorService.getSensorByDeviceAndSensor(anyLong(), anyLong()))
                .willThrow(new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."));

        mockMvc.perform(get("/api/common/device-sensor/" + deviceId + "/" + sensorId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("장비별 센서 데이터를 찾을 수 없습니다.")));
    }

    @Test
    void getSensorByDeviceNameAndSensorName() throws Exception {
        // given
        String deviceName = "test device";
        String sensorName = "test sensor";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceAndSensorNameDto deviceAndSensorNameDto = new DeviceAndSensorNameDto(deviceName, sensorName);
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto)).willReturn(deviceSensorResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(get("/api/common/device-sensor/sensor")
                        .param("deviceName", deviceName)
                        .param("sensorName", sensorName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$.offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorByDeviceNameAndSensorNameException() throws Exception {
        String deviceName = "test device";
        String sensorName = "test sensor";
        DeviceAndSensorNameDto deviceAndSensorNameDto = new DeviceAndSensorNameDto(deviceName, sensorName);

        given(deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto))
                .willThrow(new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(get("/api/common/device-sensor/sensor")
                        .param("deviceName", deviceName)
                        .param("sensorName", sensorName))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("장비별 센서 데이터를 찾을 수 없습니다.")));
    }
}