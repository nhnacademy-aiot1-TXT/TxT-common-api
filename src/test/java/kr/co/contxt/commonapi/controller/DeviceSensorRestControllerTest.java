package kr.co.contxt.commonapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.contxt.commonapi.dto.DeviceAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceAndSensorAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.exception.DeviceSensorAlreadyExistException;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DeviceSensorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DeviceSensorService deviceSensorService;
    private static final String DEVICE_SENSOR_NOT_FOUND_MESSAGE = "장비별 센서 데이터를 찾을 수 없습니다.";
    private static final String DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION = "장치별 센서 데이터가 이미 존재합니다.";

    @Test
    void getSensorListByDevice() throws Exception {
        Long deviceId = 1L;
        Long sensorId = 1L;
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .deviceId(deviceId)
                .sensorId(sensorId)
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
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceAndPlaceNameDto deviceAndPlaceNameDto = new DeviceAndPlaceNameDto(deviceName, placeName);
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .deviceId(deviceId)
                .sensorId(sensorId)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorListByDevice(deviceAndPlaceNameDto)).willReturn(List.of(deviceSensorResponse));

        mockMvc.perform(get("/api/common/device-sensor/sensors")
                        .param("deviceName", deviceName)
                        .param("placeName", placeName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$[0].offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorByDeviceAndSensor() throws Exception {
        Long deviceId = 1L;
        Long sensorId = 1L;
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .deviceId(deviceId)
                .sensorId(sensorId)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorByDeviceAndSensor(anyLong(), anyLong())).willReturn(deviceSensorResponse);

        mockMvc.perform(get("/api/common/device-sensor/" + deviceId + "/" + sensorId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId", equalTo(deviceId.intValue())))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$.offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorByDeviceAndSensorException() throws Exception {
        long deviceId = 1L;
        long sensorId = 1L;

        given(deviceSensorService.getSensorByDeviceAndSensor(anyLong(), anyLong()))
                .willThrow(new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE));

        mockMvc.perform(get("/api/common/device-sensor/" + deviceId + "/" + sensorId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo(DEVICE_SENSOR_NOT_FOUND_MESSAGE)));
    }

    @Test
    void getSensorByDeviceNameAndSensorName() throws Exception {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto = new DeviceAndSensorAndPlaceNameDto(deviceName, sensorName, placeName);
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .deviceId(deviceId)
                .sensorId(sensorId)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorAndPlaceNameDto)).willReturn(deviceSensorResponse);

        mockMvc.perform(get("/api/common/device-sensor/sensor")
                        .param("deviceName", deviceName)
                        .param("sensorName", sensorName)
                        .param("placeName", placeName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deviceId", equalTo(deviceId.intValue())))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$.offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void getSensorByDeviceNameAndSensorNameException() throws Exception {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto = new DeviceAndSensorAndPlaceNameDto(deviceName, sensorName, placeName);

        given(deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorAndPlaceNameDto))
                .willThrow(new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE));

        mockMvc.perform(get("/api/common/device-sensor/sensor")
                        .param("deviceName", deviceName)
                        .param("sensorName", sensorName)
                        .param("placeName", placeName))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo(DEVICE_SENSOR_NOT_FOUND_MESSAGE)));
    }

    @Test
    void updateSensorByDeviceAndSensor() throws Exception {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 29f;
        Float offValue = 20f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .deviceId(deviceId)
                .sensorId(sensorId)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.updateSensorByDeviceAndSensor(any()))
                .willReturn(deviceSensorResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/api/common/device-sensor")
                        .content(objectMapper.writeValueAsString(deviceSensorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deviceId", equalTo(deviceId.intValue())))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$.offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void updateSensorByDeviceAndSensorException() throws Exception {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 29f;
        Float offValue = 20f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);

        given(deviceSensorService.updateSensorByDeviceAndSensor(any()))
                .willThrow(new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/api/common/device-sensor")
                        .content(objectMapper.writeValueAsString(deviceSensorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo(DEVICE_SENSOR_NOT_FOUND_MESSAGE)));
    }

    @Test
    void addSensor() throws Exception {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 29f;
        Float offValue = 20f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);
        DeviceSensorResponse deviceSensorResponse = DeviceSensorResponse.builder()
                .deviceId(deviceId)
                .sensorId(sensorId)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorService.saveSensor(any())).willReturn(deviceSensorResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/common/device-sensor")
                        .content(objectMapper.writeValueAsString(deviceSensorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.deviceId", equalTo(deviceId.intValue())))
                .andExpect(jsonPath("$.sensorId", equalTo(sensorId.intValue())))
                .andExpect(jsonPath("$.onValue", equalTo(onValue.doubleValue())))
                .andExpect(jsonPath("$.offValue", equalTo(offValue.doubleValue())));
    }

    @Test
    void addSensorDeviceNotFoundException() throws Exception {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 29f;
        Float offValue = 20f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);

        given(deviceSensorService.saveSensor(any())).willThrow(new DeviceNotFoundException("Device를 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/common/device-sensor")
                        .content(objectMapper.writeValueAsString(deviceSensorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Device를 찾을 수 없습니다.")));
    }

    @Test
    void addSensorSensorNotFoundException() throws Exception {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 29f;
        Float offValue = 20f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);

        given(deviceSensorService.saveSensor(any())).willThrow(new SensorNotFoundException("Sensor를 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/common/device-sensor")
                        .content(objectMapper.writeValueAsString(deviceSensorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Sensor를 찾을 수 없습니다.")));
    }

    @Test
    void addSensorDeviceSensorAlreadyExistException() throws Exception {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 29f;
        Float offValue = 20f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);

        given(deviceSensorService.saveSensor(any())).willThrow(new DeviceSensorAlreadyExistException(DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/common/device-sensor")
                        .content(objectMapper.writeValueAsString(deviceSensorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo(DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION)));
    }

    @Test
    void deleteSensorsByPlaceAndDevice() throws Exception {
        String deviceName = "test device";
        String placeCode = "test place";

        doNothing().when(deviceSensorService)
                .deleteSensors(placeCode, deviceName);

        mockMvc.perform(delete("/api/common/device-sensor/" + placeCode + "/" + deviceName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteSensorsByPlaceAndDeviceException() throws Exception {
        String deviceName = "test device";
        String placeCode = "test place";

        doThrow(new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE))
                .when(deviceSensorService).deleteSensors(anyString(), anyString());

        mockMvc.perform(delete("/api/common/device-sensor/" + placeCode + "/" + deviceName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo(DEVICE_SENSOR_NOT_FOUND_MESSAGE)));
    }
}