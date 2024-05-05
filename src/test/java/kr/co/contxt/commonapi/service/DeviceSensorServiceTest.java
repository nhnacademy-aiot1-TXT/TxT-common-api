package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceSensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebMvcTest(DeviceSensorService.class)
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

        given(deviceSensorRepository.findByDevice_DeviceId(anyLong())).willReturn(List.of(deviceSensor));

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

        given(deviceSensorRepository.findByDevice_DeviceName(anyString())).willReturn(List.of(deviceSensor));

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

        given(deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(anyLong(), anyLong()))
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

        given(deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(anyLong(), anyLong()))
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

        given(deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorName(anyString(), anyString()))
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

        given(deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorName(anyString(), anyString()))
                .willReturn(Optional.empty());
        Throwable throwable = assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto));

        assertAll(
                () -> assertEquals("장비별 센서 데이터를 찾을 수 없습니다.", throwable.getMessage())
        );
    }

    @Test
    void updateSensorByDeviceAndSensor() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        Float beforeOnValue = 25F;
        Float beforeOffValue = 22F;
        Float afterOnValue = 25F;
        Float afterOffValue = 22F;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(afterOnValue, afterOffValue);
        DeviceSensor beforeDeviceSensor = DeviceSensor.builder()
                .sensor(Sensor.builder().sensorName("test").build())
                .onValue(beforeOnValue)
                .offValue(beforeOffValue)
                .build();
        DeviceSensor afterDeviceSensor = DeviceSensor.builder()
                .sensor(Sensor.builder().sensorName("test").build())
                .onValue(afterOnValue)
                .offValue(afterOffValue)
                .build();

        given(deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(deviceId, sensorId))
                .willReturn(Optional.of(beforeDeviceSensor));
        given(deviceSensorRepository.save(any()))
                .willReturn(afterDeviceSensor);

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.updateSensorByDeviceAndSensor(deviceId, sensorId, deviceSensorRequest);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals("test", deviceSensorResponse.getSensorName()),
                () -> assertEquals(afterOnValue, deviceSensorResponse.getOnValue()),
                () -> assertEquals(afterOffValue, deviceSensorResponse.getOffValue())
        );
    }

    @Test
    void updateSensorByDeviceAndSensorException() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        Float onValue = 22f;
        Float offValue = 15f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(onValue, offValue);
        given(deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(anyLong(), anyLong()))
                .willReturn(Optional.empty());

        Throwable throwable = assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.updateSensorByDeviceAndSensor(deviceId, sensorId, deviceSensorRequest));

        assertAll(
                () -> assertEquals("장비별 센서 데이터를 찾을 수 없습니다.", throwable.getMessage())
        );
    }
}