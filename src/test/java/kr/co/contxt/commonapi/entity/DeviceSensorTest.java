package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DeviceSensorTest {
    @Test
    void deviceSensorEntityTest() {
        Place place = new Place(1L, "test place", LocalTime.now());
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .deviceSensorId(1L)
                .onValue(25f)
                .offValue(20f)
                .device(new Device(1L, place, "test device", 1))
                .sensor(new Sensor(1L, "test sensor"))
                .build();

        DeviceSensorResponse deviceSensorResponse = deviceSensor.toDto();

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(deviceSensor.getDevice().getDeviceId(), deviceSensorResponse.getDeviceId()),
                () -> assertEquals(deviceSensor.getSensor().getSensorId(), deviceSensorResponse.getSensorId()),
                () -> assertEquals(deviceSensor.getSensor().getSensorName(), deviceSensorResponse.getSensorName()),
                () -> assertEquals(deviceSensor.getOnValue(), deviceSensorResponse.getOnValue()),
                () -> assertEquals(deviceSensor.getOffValue(), deviceSensorResponse.getOffValue())
        );
    }
}