package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceSensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DeviceSensorServiceTest {
    @Autowired
    private DeviceSensorService deviceSensorService;
    @MockBean
    private DeviceSensorRepository deviceSensorRepository;

    @Test
    void getSensorListByDevice() {
        Long deviceId = 1L;
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .sensor(Sensor.builder().sensorName("test").build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_Id(anyLong())).willReturn(List.of(deviceSensor));

        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceId);

        assertAll(
                () -> assertNotNull(deviceSensors),
                () -> assertFalse(deviceSensors.isEmpty()),
                () -> assertEquals(onValue, deviceSensors.get(0).getOnValue()),
                () -> assertEquals(offValue, deviceSensors.get(0).getOffValue())
        );
    }

    @Test
    void getSensorListByDeviceName() {
        String deviceName = "testName";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceNameDto deviceNameDto = new DeviceNameDto(deviceName);
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .sensor(Sensor.builder().sensorName("test").build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_Name(anyString())).willReturn(List.of(deviceSensor));

        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceNameDto);

        assertAll(
                () -> assertNotNull(deviceSensors),
                () -> assertFalse(deviceSensors.isEmpty()),
                () -> assertEquals(onValue, deviceSensors.get(0).getOnValue()),
                () -> assertEquals(offValue, deviceSensors.get(0).getOffValue())
        );
    }

    @Test
    void getSensorByDeviceAndSensor() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .sensor(Sensor.builder().sensorName("test").build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_IdAndSensor_SensorId(anyLong(), anyLong()))
                .willReturn(Optional.of(deviceSensor));

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.getSensorByDeviceAndSensor(deviceId, sensorId);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(onValue, deviceSensorResponse.getOnValue()),
                () -> assertEquals(offValue, deviceSensorResponse.getOffValue())
        );
    }

    @Test
    void getSensorByDeviceAndSensorThrowException() {
        Long deviceId = 1L;
        Long sensorId = 1L;

        given(deviceSensorRepository.findByDevice_IdAndSensor_SensorId(anyLong(), anyLong()))
                .willReturn(Optional.empty());

        Throwable throwable = assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.getSensorByDeviceAndSensor(deviceId, sensorId));

        assertAll(
                () -> assertEquals("장비별 센서 데이터를 찾을 수 없습니다.", throwable.getMessage())
        );
    }

    @Test
    void getSensorByDeviceNameAndSensorName() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceAndSensorNameDto deviceAndSensorNameDto = new DeviceAndSensorNameDto(deviceName, sensorName);
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .sensor(Sensor.builder().sensorName("test").build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_NameAndSensor_SensorName(anyString(), anyString()))
                .willReturn(Optional.of(deviceSensor));

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(onValue, deviceSensorResponse.getOnValue()),
                () -> assertEquals(offValue, deviceSensorResponse.getOffValue())
        );
    }

    @Test
    void getSensorByDeviceNameAndSensorNameThrowException() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        DeviceAndSensorNameDto deviceAndSensorNameDto = new DeviceAndSensorNameDto(deviceName, sensorName);

        given(deviceSensorRepository.findByDevice_NameAndSensor_SensorName(anyString(), anyString()))
                .willReturn(Optional.empty());

        Throwable throwable = assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto));

        assertAll(
                () -> assertEquals("장비별 센서 데이터를 찾을 수 없습니다.", throwable.getMessage())
        );
    }
}