package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Device;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeviceRepositoryTest {
    @Mock
    private DeviceRepository deviceRepository;

    @Test
    void findByPlace() {
        Device device = Device.builder()
                .deviceId(1L)
                .deviceName("test")
                .aiMode(1)
                .build();

        given(deviceRepository.findByPlace_PlaceId(anyLong())).willReturn(List.of(device));

        Device result = deviceRepository.findByPlace_PlaceId(1L).get(0);

        assertAll(() -> {
            assertEquals(device.getDeviceId(), result.getDeviceId());
            assertEquals(device.getDeviceName(), result.getDeviceName());
        });
    }

    @Test
    void findByPlaceAndName() {
        Device device = Device.builder()
                .deviceId(1L)
                .deviceName("test")
                .aiMode(1)
                .build();

        given(deviceRepository.findByPlace_PlaceNameAndDeviceName(anyString(), anyString())).willReturn(Optional.of(device));

        Device result = deviceRepository.findByPlace_PlaceNameAndDeviceName("test place", "test").get();

        assertAll(() -> {
            assertEquals(device.getDeviceId(), result.getDeviceId());
            assertEquals(device.getDeviceName(), result.getDeviceName());
        });
    }

    @Test
    void existsByDeviceNameAndPlace_PlaceId() {
        given(deviceRepository.existsByDeviceNameAndPlace_PlaceId(anyString(), anyLong())).willReturn(true);

        assertAll(() -> {
            assertTrue(deviceRepository.existsByDeviceNameAndPlace_PlaceId("test", 1L));
        });
    }
}