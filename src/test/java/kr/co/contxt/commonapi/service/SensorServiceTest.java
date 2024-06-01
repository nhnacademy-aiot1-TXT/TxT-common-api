package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.SensorAlreadyExistException;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@WebMvcTest(SensorService.class)
class SensorServiceTest {
    @Autowired
    private SensorService sensorService;
    @MockBean
    private SensorRepository sensorRepository;
    private static final String SENSOR_NOT_FOUND_MESSAGE = "센서를 찾을 수 없습니다.";
    private static final String SENSOR_ALREADY_EXIST_EXCEPTION = "센서가 이미 존재합니다.";

    @Test
    void getAllSensors() {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorRepository.findAll()).willReturn(List.of(sensor));

        // when
        List<SensorResponse> sensors = sensorService.getAllSensors();

        // then
        assertAll(
                () -> assertNotNull(sensors),
                () -> assertFalse(sensors.isEmpty()),
                () -> assertEquals(sensorId, sensors.get(0).getSensorId()),
                () -> assertEquals(sensorName, sensors.get(0).getSensorName())
        );
    }

    @Test
    void getSensor() {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorRepository.findById(anyLong())).willReturn(Optional.of(sensor));

        // when
        SensorResponse sensorResponse = sensorService.getSensor(sensorId);

        // then
        assertAll(
                () -> assertNotNull(sensorResponse),
                () -> assertEquals(sensorId, sensorResponse.getSensorId()),
                () -> assertEquals(sensorName, sensorResponse.getSensorName())
        );
    }

    @Test
    void getSensorException() {
        given(sensorRepository.findById(1L)).willThrow(new SensorNotFoundException(SENSOR_NOT_FOUND_MESSAGE));

        Throwable throwable = assertThrows(SensorNotFoundException.class, () -> sensorService.getSensor(1L));

        assertAll(
                () -> assertEquals(SENSOR_NOT_FOUND_MESSAGE, throwable.getMessage())
        );
    }

    @Test
    void saveSensor() {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorRepository.existsBySensorName(anyString())).willReturn(false);
        given(sensorRepository.save(sensor)).willReturn(sensor);

        // when
        SensorResponse saveSensor = sensorService.saveSensor(sensor);

        // then
        assertAll(
                () -> assertNotNull(saveSensor),
                () -> assertEquals(sensorId, saveSensor.getSensorId()),
                () -> assertEquals(sensorName, saveSensor.getSensorName())
        );
    }

    @Test
    void saveSensorSensorAlreadyExistException() {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorRepository.existsBySensorName(anyString())).willReturn(true);

        Throwable throwable = assertThrows(SensorAlreadyExistException.class, () -> sensorService.saveSensor(sensor));

        assertAll(
                () -> assertEquals(SENSOR_ALREADY_EXIST_EXCEPTION, throwable.getMessage())
        );
    }

    @Test
    void updateSensor() {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();
        SensorRequest sensorRequest = new SensorRequest();
        sensorRequest.setSensorName(sensorName);

        given(sensorRepository.findById(anyLong())).willReturn(Optional.of(sensor));
        given(sensorRepository.save(sensor)).willReturn(sensor);

        // when
        SensorResponse updateSensor = sensorService.updateSensor(sensorId, sensorRequest);

        // then
        assertAll(
                () -> assertNotNull(updateSensor),
                () -> assertEquals(sensorId, updateSensor.getSensorId()),
                () -> assertEquals(sensorName, updateSensor.getSensorName())
        );
    }

    @Test
    void updateSensorException() {
        SensorRequest sensorRequest = new SensorRequest();

        given(sensorRepository.findById(1L)).willThrow(new SensorNotFoundException(SENSOR_NOT_FOUND_MESSAGE));

        Throwable throwable = assertThrows(SensorNotFoundException.class, () -> sensorService.updateSensor(1L, sensorRequest));

        assertAll(
                () -> assertEquals(SENSOR_NOT_FOUND_MESSAGE, throwable.getMessage())
        );
    }
}