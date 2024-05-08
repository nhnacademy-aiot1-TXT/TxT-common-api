package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.SensorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SensorTest {
    @Test
    void sensorEntityTest() {
        Sensor sensor = Sensor.builder()
                .sensorId(1L)
                .sensorName("test")
                .build();

        SensorResponse sensorResponse = sensor.toDto();

        assertNotNull(sensorResponse);
        assertEquals(sensor.getSensorId(), sensorResponse.getSensorId());
        assertEquals(sensor.getSensorName(), sensorResponse.getSensorName());
    }
}