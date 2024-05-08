package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeviceSensorTest {
    @Test
    void deviceSensorEntityTest() {
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .deviceSensorId(1L)
                .onValue(25f)
                .offValue(20f)
                .device(new Device(1L, "test device", LocalTime.now()))
                .sensor(new Sensor(1L, "test sensor"))
                .build();

        DeviceSensorResponse deviceSensorResponse = deviceSensor.toDto();

        assertNotNull(deviceSensorResponse);
        assertEquals(deviceSensor.getDevice().getDeviceId(), deviceSensorResponse.getDeviceId());
        assertEquals(deviceSensor.getSensor().getSensorId(), deviceSensorResponse.getSensorId());
        assertEquals(deviceSensor.getSensor().getSensorName(), deviceSensorResponse.getSensorName());
        assertEquals(deviceSensor.getOnValue(), deviceSensorResponse.getOnValue());
        assertEquals(deviceSensor.getOffValue(), deviceSensorResponse.getOffValue());
    }
}