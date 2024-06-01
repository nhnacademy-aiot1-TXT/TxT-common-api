package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.exception.DeviceAlreadyExistException;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(deviceList, responseEntity.getBody())
        );
    }

    @Test
    void getDeviceById() {
        DeviceResponse device = new DeviceResponse(1L, 1L, "test", 1);

        given(deviceService.getDeviceById(anyLong())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.getDeviceById(1L);

        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(device, responseEntity.getBody())
        );
    }

    @Test
    void getDeviceByName() {
        DeviceResponse device = new DeviceResponse(1L, 1L, "test", 1);

        given(deviceService.getDeviceByPlaceAndName(anyString(), anyString())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.getDeviceByName("test place", "test device");

        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(device, responseEntity.getBody())
        );
    }

    @Test
    void getDeviceByIdException() {
        given(deviceService.getDeviceById(anyLong())).willThrow(DeviceNotFoundException.class);

        assertAll(
                () -> assertThrows(DeviceNotFoundException.class, () -> deviceRestController.getDeviceById(1L))
        );
    }

    @Test
    void getDeviceByNameException() {
        given(deviceService.getDeviceByPlaceAndName(anyString(), anyString())).willThrow(DeviceNotFoundException.class);

        assertAll(
                () -> assertThrows(DeviceNotFoundException.class, () -> deviceRestController.getDeviceByName("test place", "test device"))
        );
    }

    @Test
    void addDevice() {
        DeviceResponse device = new DeviceResponse(1L, 1L, "test", 1);

        given(deviceService.addDevice(any())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.addDevice(new DeviceRequest());
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertEquals(device, responseEntity.getBody())
        );
    }

    @Test
    void addDeviceException() {
        given(deviceService.addDevice(any())).willThrow(DeviceAlreadyExistException.class);

        assertAll(
                () -> assertThrows(DeviceAlreadyExistException.class, () -> deviceRestController.addDevice(new DeviceRequest()))
        );
    }

    @Test
    void updateDevice() {
        DeviceResponse device = new DeviceResponse(1L, 1L, "test", 1);

        given(deviceService.updateDevice(anyLong(), any())).willReturn(device);

        ResponseEntity<DeviceResponse> responseEntity = deviceRestController.updateDevice(1L, new DeviceRequest());

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertEquals(device, responseEntity.getBody())
        );
    }

    @Test
    void updateDeviceException() {
        DeviceRequest deviceRequest = new DeviceRequest();

        given(deviceService.updateDevice(anyLong(), any())).willThrow(DeviceNotFoundException.class);

        assertAll(
                () -> assertThrows(DeviceNotFoundException.class, () -> deviceRestController.updateDevice(1L, deviceRequest))
        );
    }

}