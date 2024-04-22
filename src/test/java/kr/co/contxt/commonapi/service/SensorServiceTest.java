package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SensorServiceTest {
    @Autowired
    private SensorService sensorService;
    @MockBean
    private SensorRepository sensorRepository;

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
    void saveSensor() {
        // given
        Long sensorId = 1L;
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();

        given(sensorRepository.save(sensor)).willReturn(sensor);

        // when
        Sensor saveSensor = sensorService.saveSensor(sensor);

        // then
        assertAll(
                () -> assertNotNull(saveSensor),
                () -> assertEquals(sensorId, saveSensor.getSensorId()),
                () -> assertEquals(sensorName, saveSensor.getSensorName())
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
        Sensor updateSensor = sensorService.updateSensor(sensorId, sensorRequest);

        // then
        assertAll(
                () -> assertNotNull(updateSensor),
                () -> assertEquals(sensorId, updateSensor.getSensorId()),
                () -> assertEquals(sensorName, updateSensor.getSensorName())
        );
    }
}