package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.entity.Device;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest {
    @Mock
    private DeviceRepository deviceRepository;
    @InjectMocks
    private DeviceServiceImpl deviceService;

    @Test
    void getDeviceList() {
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device(1L, "test1", LocalTime.of(0, 10, 0)));
        deviceList.add(new Device(2L, "test2", LocalTime.of(0, 20, 0)));
        deviceList.add(new Device(3L, "test3", LocalTime.of(0, 30, 0)));

        given(deviceRepository.findAll()).willReturn(deviceList);

        List<DeviceResponse> result = deviceService.getDeviceList();

        assertEquals(deviceList.size(), result.size());
        assertEquals(deviceList.stream().map(Device::toDto).collect(Collectors.toList()), result);
    }

    @Test
    void getDevice() {
        Device device = new Device(1L, "test1", LocalTime.of(0, 10, 0));

        given(deviceRepository.findById(anyLong())).willReturn(Optional.of(device));

        DeviceResponse result = deviceService.getDevice(1L);

        assertEquals(device.getId(), result.getDeviceId());
        assertEquals(device.getName(), result.getDeviceName());
        assertEquals(device.getCycle(), result.getCycle());
    }

    @Test
    void updateDevice() {
        Device device = new Device(1L, "test1", LocalTime.of(0, 10, 0));

        given(deviceRepository.findById(anyLong())).willReturn(Optional.of(device));
        given(deviceRepository.save(any())).willReturn(device);
        
        DeviceResponse result = deviceService.updateDevice(1L, new DeviceRequest());

        assertEquals(device.getId(), result.getDeviceId());
        assertEquals(device.getName(), result.getDeviceName());
        assertEquals(device.getCycle(), result.getCycle());
    }

    @Test
    void addDevice() {
        Device device = new Device(1L, "test1", LocalTime.of(0, 10, 0));

        given(deviceRepository.save(any())).willReturn(device);

        DeviceResponse result = deviceService.addDevice(new DeviceRequest());

        assertEquals(device.getId(), result.getDeviceId());
        assertEquals(device.getName(), result.getDeviceName());
        assertEquals(device.getCycle(), result.getCycle());
    }
}