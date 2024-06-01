package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceAndSensorAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.exception.DeviceSensorAlreadyExistException;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.repository.DeviceSensorRepository;
import kr.co.contxt.commonapi.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@WebMvcTest(DeviceSensorService.class)
class DeviceSensorServiceTest {
    @Autowired
    private DeviceSensorService deviceSensorService;
    @MockBean
    private DeviceSensorRepository deviceSensorRepository;
    @MockBean
    private DeviceRepository deviceRepository;
    @MockBean
    private SensorRepository sensorRepository;
    private static final String DEVICE_SENSOR_NOT_FOUND_MESSAGE = "장비별 센서 데이터를 찾을 수 없습니다.";
    private static final String DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION = "장치별 센서 데이터가 이미 존재합니다.";

    @Test
    void getSensorListByDevice() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(Device.builder().deviceId(deviceId).deviceName(deviceName).build())
                .sensor(Sensor.builder().sensorId(sensorId).sensorName(sensorName).build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_DeviceId(anyLong())).willReturn(List.of(deviceSensor));

        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceId);

        assertAll(
                () -> assertNotNull(deviceSensors),
                () -> assertFalse(deviceSensors.isEmpty()),
                () -> assertEquals(deviceId, deviceSensors.get(0).getDeviceId()),
                () -> assertEquals(sensorId, deviceSensors.get(0).getSensorId()),
                () -> assertEquals(onValue, deviceSensors.get(0).getOnValue()),
                () -> assertEquals(offValue, deviceSensors.get(0).getOffValue())
        );
    }

    @Test
    void getSensorListByDeviceName() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceAndPlaceNameDto deviceAndPlaceNameDto = new DeviceAndPlaceNameDto(deviceName, placeName);
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(Device.builder().deviceId(deviceId).deviceName(deviceName).build())
                .sensor(Sensor.builder().sensorId(sensorId).sensorName(sensorName).build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_DeviceNameAndDevice_Place_PlaceCode(anyString(), anyString())).willReturn(List.of(deviceSensor));

        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceAndPlaceNameDto);

        assertAll(
                () -> assertNotNull(deviceSensors),
                () -> assertFalse(deviceSensors.isEmpty()),
                () -> assertEquals(deviceId, deviceSensors.get(0).getDeviceId()),
                () -> assertEquals(sensorId, deviceSensors.get(0).getSensorId()),
                () -> assertEquals(onValue, deviceSensors.get(0).getOnValue()),
                () -> assertEquals(offValue, deviceSensors.get(0).getOffValue())
        );
    }

    @Test
    void getSensorByDeviceAndSensor() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(Device.builder().deviceId(deviceId).deviceName(deviceName).build())
                .sensor(Sensor.builder().sensorId(sensorId).sensorName(sensorName).build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(anyLong(), anyLong()))
                .willReturn(Optional.of(deviceSensor));

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.getSensorByDeviceAndSensor(deviceId, sensorId);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(deviceId, deviceSensorResponse.getDeviceId()),
                () -> assertEquals(sensorId, deviceSensorResponse.getSensorId()),
                () -> assertEquals(sensorName, deviceSensorResponse.getSensorName()),
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
                () -> assertEquals(DEVICE_SENSOR_NOT_FOUND_MESSAGE, throwable.getMessage())
        );
    }

    @Test
    void getSensorByDeviceNameAndSensorName() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto = new DeviceAndSensorAndPlaceNameDto(deviceName, sensorName, placeName);
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(Device.builder().deviceId(deviceId).deviceName(deviceName).build())
                .sensor(Sensor.builder().sensorId(sensorId).sensorName(sensorName).build())
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(anyString(), anyString(), anyString()))
                .willReturn(Optional.of(deviceSensor));

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorAndPlaceNameDto);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(deviceId, deviceSensorResponse.getDeviceId()),
                () -> assertEquals(sensorId, deviceSensorResponse.getSensorId()),
                () -> assertEquals(onValue, deviceSensorResponse.getOnValue()),
                () -> assertEquals(offValue, deviceSensorResponse.getOffValue())
        );
    }

    @Test
    void getSensorByDeviceNameAndSensorNameThrowException() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto = new DeviceAndSensorAndPlaceNameDto(deviceName, sensorName, placeName);

        given(deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(anyString(), anyString(), anyString()))
                .willReturn(Optional.empty());
        Throwable throwable = assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorAndPlaceNameDto));

        assertAll(
                () -> assertEquals(DEVICE_SENSOR_NOT_FOUND_MESSAGE, throwable.getMessage())
        );
    }

    @Test
    void updateSensorByDeviceAndSensor() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float beforeOnValue = 25F;
        Float beforeOffValue = 22F;
        Float afterOnValue = 25F;
        Float afterOffValue = 22F;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, afterOnValue, afterOffValue);
        DeviceSensor beforeDeviceSensor = DeviceSensor.builder()
                .device(Device.builder().deviceId(deviceId).deviceName(deviceName).build())
                .sensor(Sensor.builder().sensorId(sensorId).sensorName(sensorName).build())
                .onValue(beforeOnValue)
                .offValue(beforeOffValue)
                .build();
        DeviceSensor afterDeviceSensor = DeviceSensor.builder()
                .device(Device.builder().deviceId(deviceId).deviceName(deviceName).build())
                .sensor(Sensor.builder().sensorId(sensorId).sensorName(sensorName).build())
                .onValue(afterOnValue)
                .offValue(afterOffValue)
                .build();

        given(deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(anyString(), anyString(), anyString()))
                .willReturn(Optional.of(beforeDeviceSensor));
        given(deviceSensorRepository.save(any()))
                .willReturn(afterDeviceSensor);

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.updateSensorByDeviceAndSensor(deviceSensorRequest);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(deviceId, deviceSensorResponse.getDeviceId()),
                () -> assertEquals(sensorId, deviceSensorResponse.getSensorId()),
                () -> assertEquals(sensorName, deviceSensorResponse.getSensorName()),
                () -> assertEquals(afterOnValue, deviceSensorResponse.getOnValue()),
                () -> assertEquals(afterOffValue, deviceSensorResponse.getOffValue())
        );
    }

    @Test
    void updateSensorByDeviceAndSensorException() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 22f;
        Float offValue = 15f;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);
        given(deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(anyString(), anyString(), anyString()))
                .willReturn(Optional.empty());

        Throwable throwable = assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.updateSensorByDeviceAndSensor(deviceSensorRequest));

        assertAll(
                () -> assertEquals(DEVICE_SENSOR_NOT_FOUND_MESSAGE, throwable.getMessage())
        );
    }

    @Test
    void saveSensor() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        Device device = Device.builder().deviceId(deviceId).deviceName(deviceName).build();
        Sensor sensor = Sensor.builder().sensorId(sensorId).sensorName(sensorName).build();
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(device)
                .sensor(sensor)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceRepository.findByPlace_PlaceCodeAndDeviceName(anyString(), anyString()))
                .willReturn(Optional.of(device));
        given(sensorRepository.findBySensorName(anyString()))
                .willReturn(Optional.of(sensor));
        given(deviceSensorRepository.existsByDevice_DeviceIdAndSensor_SensorId(anyLong(), anyLong()))
                .willReturn(false);
        given(deviceSensorRepository.save(any()))
                .willReturn(deviceSensor);

        DeviceSensorResponse deviceSensorResponse = deviceSensorService.saveSensor(deviceSensorRequest);

        assertAll(
                () -> assertNotNull(deviceSensorResponse),
                () -> assertEquals(deviceId, deviceSensorResponse.getDeviceId()),
                () -> assertEquals(sensorId, deviceSensorResponse.getSensorId()),
                () -> assertEquals(sensorName, deviceSensorResponse.getSensorName()),
                () -> assertEquals(onValue, deviceSensorResponse.getOnValue()),
                () -> assertEquals(offValue, deviceSensorResponse.getOffValue())
        );
    }

    @Test
    void saveSensorDeviceNotFoundException() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);

        given(deviceRepository.findByPlace_PlaceCodeAndDeviceName(anyString(), anyString()))
                .willReturn(Optional.empty());

        Throwable throwable = assertThrows(DeviceNotFoundException.class, () -> deviceSensorService.saveSensor(deviceSensorRequest));

        assertAll(
                () -> assertEquals("Device를 찾을 수 없습니다.", throwable.getMessage())
        );
    }

    @Test
    void saveSensorSensorNotFoundException() {
        Long deviceId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        Device device = Device.builder().deviceId(deviceId).deviceName(deviceName).build();
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);

        given(deviceRepository.findByPlace_PlaceCodeAndDeviceName(anyString(), anyString()))
                .willReturn(Optional.of(device));
        given(sensorRepository.findBySensorName(anyString()))
                .willReturn(Optional.empty());

        Throwable throwable = assertThrows(SensorNotFoundException.class, () -> deviceSensorService.saveSensor(deviceSensorRequest));

        assertAll(
                () -> assertEquals("Sensor를 찾을 수 없습니다.", throwable.getMessage())
        );
    }

    @Test
    void saveSensorDeviceSensorAlreadyExistException() {
        Long deviceId = 1L;
        Long sensorId = 1L;
        String deviceName = "test device";
        String sensorName = "test sensor";
        String placeName = "test place";
        Float onValue = 25F;
        Float offValue = 22F;
        Device device = Device.builder().deviceId(deviceId).deviceName(deviceName).build();
        Sensor sensor = Sensor.builder().sensorId(sensorId).sensorName(sensorName).build();
        DeviceSensorRequest deviceSensorRequest = new DeviceSensorRequest(deviceName, sensorName, placeName, onValue, offValue);
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(device)
                .sensor(sensor)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        given(deviceRepository.findByPlace_PlaceCodeAndDeviceName(anyString(), anyString()))
                .willReturn(Optional.of(device));
        given(sensorRepository.findBySensorName(anyString()))
                .willReturn(Optional.of(sensor));
        given(deviceSensorRepository.existsByDevice_DeviceIdAndSensor_SensorId(anyLong(), anyLong()))
                .willReturn(true);

        Throwable throwable = assertThrows(DeviceSensorAlreadyExistException.class, () -> deviceSensorService.saveSensor(deviceSensorRequest));

        assertAll(
                () -> assertEquals(DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION, throwable.getMessage())
        );
    }

    @Test
    void deleteSensors() {
        String deviceName = "test device";
        String placeCode = "test place";

        doNothing().when(deviceSensorRepository).deleteAllByDevice_Place_PlaceCodeAndDevice_DeviceName(placeCode, deviceName);

        assertAll(
                () -> assertDoesNotThrow(() -> deviceSensorService.deleteSensors(placeCode, deviceName))
        );
    }

    @Test
    void deleteSensorsException() {
        String deviceName = "test device";
        String placeCode = "test place";

        doThrow(new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE)).when(deviceSensorRepository).deleteAllByDevice_Place_PlaceCodeAndDevice_DeviceName(placeCode, deviceName);

        assertAll(
                () -> assertThrows(DeviceSensorNotFoundException.class, () -> deviceSensorService.deleteSensors(placeCode, deviceName))
        );
    }
}