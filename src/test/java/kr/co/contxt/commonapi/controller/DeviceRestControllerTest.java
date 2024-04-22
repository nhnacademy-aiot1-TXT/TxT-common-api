package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeviceRestControllerTest {
    @Mock
    private DeviceService deviceService;
    @InjectMocks
    private DeviceRestController deviceRestController;

    @Test
    void getDeviceList() {
        List<DeviceResponse> deviceList = List.of(new DeviceResponse());

        given(deviceService.getDeviceList()).willReturn(deviceList);

        ResponseEntity<List<DeviceResponse>> responseEntity = deviceRestController.getDeviceList();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(deviceList, responseEntity.getBody());
    }

    @Test
    void getDevice() {
        DeviceResponse device = new DeviceResponse(1L, "test", LocalTime.of(0, 30, 0));

        given(deviceService.getDevice(anyLong())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.getDevice(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(device, responseEntity.getBody());
    }

    @Test
    void addDevice() {
        DeviceResponse device = new DeviceResponse(1L, "test", LocalTime.of(0, 30, 0));

        given(deviceService.addDevice(any())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.addDevice(new DeviceRequest());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(device, responseEntity.getBody());
    }

    @Test
    void updateDevice() {
        DeviceResponse device = new DeviceResponse(1L, "test", LocalTime.of(0, 30, 0));

        given(deviceService.updateDevice(anyLong(), any())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.updateDevice(1L, new DeviceRequest());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(device, responseEntity.getBody());
    }
}