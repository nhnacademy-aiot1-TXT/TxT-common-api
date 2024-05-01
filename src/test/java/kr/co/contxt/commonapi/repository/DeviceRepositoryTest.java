package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeviceRepositoryTest {
    @Mock
    private DeviceRepository deviceRepository;

    @Test
    void findByName() {
        Device device = Device.builder()
                .deviceId(1L)
                .deviceName("test")
                .cycle(LocalTime.of(0, 10))
                .build();

        given(deviceRepository.findByDeviceName(anyString())).willReturn(Optional.of(device));

        Device result = deviceRepository.findByDeviceName("test").get();

        assertAll(() -> {
            assertEquals(device.getDeviceId(), result.getDeviceId());
            assertEquals(device.getDeviceName(), result.getDeviceName());
            assertEquals(device.getCycle(), result.getCycle());
        });
    }
}