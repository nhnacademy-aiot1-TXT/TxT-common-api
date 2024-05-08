package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.DeviceResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {
    @Test
    void deviceEntityTest() {
        Device device = Device.builder()
                .deviceId(1L)
                .deviceName("Test Device")
                .cycle(LocalTime.of(10, 0))
                .build();

        DeviceResponse deviceResponse = device.toDto();
        
        assertAll(
                () -> assertNotNull(deviceResponse),
                () -> assertEquals(device.getDeviceId(), deviceResponse.getDeviceId()),
                () -> assertEquals(device.getDeviceName(), deviceResponse.getDeviceName()),
                () -> assertEquals(device.getCycle(), deviceResponse.getCycle())
        );
    }
}