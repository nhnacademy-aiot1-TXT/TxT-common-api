package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.Place;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.service.impl.DeviceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {
    @Mock
    private DeviceRepository deviceRepository;
    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    void getDeviceList() {
        Place place = new Place(1L, "test place", LocalTime.of(0, 10, 0));
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device(1L, place, "test1"));
        deviceList.add(new Device(2L, place, "test2"));
        deviceList.add(new Device(3L, place, "test3"));

        given(deviceRepository.findAll()).willReturn(deviceList);

        List<DeviceResponse> result = deviceService.getDeviceList();

        assertAll(
                () -> assertEquals(deviceList.size(), result.size()),
                () -> assertEquals(deviceList.stream().map(Device::toDto).collect(Collectors.toList()), result)
        );
    }

    @Test
    void getDeviceById() {
        Place place = new Place(1L, "test place", LocalTime.of(0, 10, 0));
        Device device = new Device(1L, place, "test1");

        given(deviceRepository.findById(anyLong())).willReturn(Optional.of(device));

        DeviceResponse result = deviceService.getDeviceById(1L);

        assertAll(
                () -> assertEquals(device.getDeviceId(), result.getDeviceId()),
                () -> assertEquals(device.getDeviceName(), result.getDeviceName())
        );
    }

    @Test
    void getDeviceByName() {
        Place place = new Place(1L, "test place", LocalTime.of(0, 10, 0));
        Device device = new Device(1L, place, "test1");

        given(deviceRepository.findByPlace_PlaceNameAndDeviceName(anyString(), anyString())).willReturn(Optional.of(device));

        DeviceResponse result = deviceService.getDeviceByPlaceAndName("test place", "test1");

        assertAll(
                () -> assertEquals(device.getDeviceId(), result.getDeviceId()),
                () -> assertEquals(device.getDeviceName(), result.getDeviceName())
        );
    }

    @Test
    void getDeviceByIdException() {
        given(deviceRepository.findById(anyLong())).willThrow(DeviceNotFoundException.class);

        assertAll(
                () -> assertThrows(DeviceNotFoundException.class, () -> deviceService.getDeviceById(1L))
        );
    }

    @Test
    void getDeviceByNameException() {
        given(deviceRepository.findByPlace_PlaceNameAndDeviceName(anyString(), anyString())).willThrow(DeviceNotFoundException.class);

        assertAll(
                () -> assertThrows(DeviceNotFoundException.class, () -> deviceService.getDeviceByPlaceAndName("test place", "test"))
        );
    }

    @Test
    void addDevice() {
        Place place = new Place(1L, "test place", LocalTime.of(0, 10, 0));
        Device device = new Device(1L, place, "test1");

        given(deviceRepository.save(any())).willReturn(device);

        DeviceResponse result = deviceService.addDevice(new DeviceRequest());

        assertAll(
                () -> assertEquals(device.getDeviceId(), result.getDeviceId()),
                () -> assertEquals(device.getDeviceName(), result.getDeviceName())
        );
    }

    @Test
    void updateDevice() {
        Place place = new Place(1L, "test place", LocalTime.of(0, 10, 0));
        Device device = new Device(1L, place, "test1");

        given(deviceRepository.findById(anyLong())).willReturn(Optional.of(device));
        given(deviceRepository.save(any())).willReturn(device);

        DeviceResponse result = deviceService.updateDevice(1L, new DeviceRequest());

        assertAll(
                () -> assertEquals(device.getDeviceId(), result.getDeviceId()),
                () -> assertEquals(device.getDeviceName(), result.getDeviceName())
        );
    }

    @Test
    void updateDeviceException() {
        given(deviceRepository.findById(anyLong())).willThrow(DeviceNotFoundException.class);

        assertAll(
                () -> assertThrows(DeviceNotFoundException.class, () -> deviceService.updateDevice(1L, new DeviceRequest()))
        );
    }
}