package kr.co.contxt.commonapi.service.impl;

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
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DeviceSensor service 구현 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DeviceSensorServiceImpl implements DeviceSensorService {
    private final DeviceSensorRepository deviceSensorRepository;
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;
    private static final String DEVICE_SENSOR_NOT_FOUND_MESSAGE = "장비별 센서 데이터를 찾을 수 없습니다.";
    private static final String DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION = "장치별 센서 데이터가 이미 존재합니다.";

    /**
     * DeviceSensor 리스트 조회 메서드
     *
     * @param deviceId the device id
     * @return deviceSensor list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getSensorListByDevice",
            key = "#deviceId",
            unless = "#result == null"
    )
    public List<DeviceSensorResponse> getSensorListByDevice(Long deviceId) {
        return deviceSensorRepository.findByDevice_DeviceId(deviceId)
                .stream()
                .map(DeviceSensor::toDto)
                .collect(Collectors.toList());
    }

    /**
     * DeviceSensor 리스트 조회 메서드
     *
     * @param deviceAndPlaceNameDto the device and place name dto
     * @return deviceSensor list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getSensorListByDeviceName",
            key = "#deviceAndPlaceNameDto.getDeviceName().concat(':').concat(#deviceAndPlaceNameDto.getPlaceName())",
            unless = "#result == null"
    )
    public List<DeviceSensorResponse> getSensorListByDevice(DeviceAndPlaceNameDto deviceAndPlaceNameDto) {
        return deviceSensorRepository.findByDevice_DeviceNameAndDevice_Place_PlaceCode(
                        deviceAndPlaceNameDto.getDeviceName(),
                        deviceAndPlaceNameDto.getPlaceName()
                )
                .stream()
                .map(DeviceSensor::toDto)
                .collect(Collectors.toList());
    }

    /**
     * DeviceSensor 단일 조회 메서드
     *
     * @param deviceId the device id
     * @param sensorId the sensor id
     * @return deviceSensor
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getSensorByDeviceAndSensor",
            key = "#deviceId.toString().concat(':').concat(#sensorId)",
            unless = "#result == null"
    )
    public DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId) {
        return deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(deviceId, sensorId)
                .orElseThrow(() -> new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE))
                .toDto();
    }

    /**
     * DeviceSensor 단일 조회 메서드
     *
     * @param deviceAndSensorAndPlaceNameDto the device and sensor and place name dto
     * @return deviceSensor
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getSensorByDeviceAndSensorName",
            key = "#deviceAndSensorAndPlaceNameDto.getDeviceName().concat(':').concat(#deviceAndSensorAndPlaceNameDto.getSensorName()).concat(':').concat(#deviceAndSensorAndPlaceNameDto.getPlaceName())",
            unless = "#result == null"
    )
    public DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto) {
        return deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(
                        deviceAndSensorAndPlaceNameDto.getDeviceName(),
                        deviceAndSensorAndPlaceNameDto.getSensorName(),
                        deviceAndSensorAndPlaceNameDto.getPlaceName()
                )
                .orElseThrow(() -> new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE))
                .toDto();
    }

    /**
     * DeviceSensor 업데이트 메서드
     *
     * @param deviceSensorRequest 장비별 센서 on/off dto
     * @return deviceSensor
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getSensorListByDevice",
                            allEntries = true
                    ),
                    @CacheEvict(
                            value = "getSensorListByDeviceName",
                            key = "#deviceSensorRequest.getDeviceName().concat(':').concat(#deviceSensorRequest.getPlaceName())"
                    ),
                    @CacheEvict(
                            value = "getSensorByDeviceAndSensor",
                            allEntries = true
                    )
            },
            put = {
                    @CachePut(
                            value = "getSensorByDeviceAndSensorName",
                            key = "#deviceSensorRequest.getDeviceName().concat(':').concat(#deviceSensorRequest.getSensorName()).concat(':').concat(#deviceSensorRequest.getPlaceName())",
                            unless = "#result == null"
                    )
            }
    )
    public DeviceSensorResponse updateSensorByDeviceAndSensor(DeviceSensorRequest deviceSensorRequest) {
        DeviceSensor deviceSensor = deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(
                        deviceSensorRequest.getDeviceName(),
                        deviceSensorRequest.getSensorName(),
                        deviceSensorRequest.getPlaceName()
                )
                .orElseThrow(() -> new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE));

        DeviceSensor build = deviceSensor.toBuilder()
                .onValue(deviceSensorRequest.getOnValue())
                .offValue(deviceSensorRequest.getOffValue())
                .build();

        return deviceSensorRepository.save(build)
                .toDto();
    }

    /**
     * DeviceSensor 추가 메서드
     *
     * @param deviceSensorRequest 장비별 센서 on/off dto
     * @return deviceSensor
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getSensorListByDevice",
                            allEntries = true
                    ),
                    @CacheEvict(
                            value = "getSensorListByDeviceName",
                            key = "#deviceSensorRequest.getDeviceName().concat(':').concat(#deviceSensorRequest.getPlaceName())"
                    )
            }
    )
    public DeviceSensorResponse saveSensor(DeviceSensorRequest deviceSensorRequest) {
        Device device = deviceRepository.findByPlace_PlaceCodeAndDeviceName(deviceSensorRequest.getPlaceName(), deviceSensorRequest.getDeviceName())
                .orElseThrow(() -> new DeviceNotFoundException("Device를 찾을 수 없습니다."));
        Sensor sensor = sensorRepository.findBySensorName(deviceSensorRequest.getSensorName())
                .orElseThrow(() -> new SensorNotFoundException("Sensor를 찾을 수 없습니다."));

        if (deviceSensorRepository.existsByDevice_DeviceIdAndSensor_SensorId(device.getDeviceId(), sensor.getSensorId())) {
            throw new DeviceSensorAlreadyExistException(DEVICE_SENSOR_ALREADY_EXIST_EXCEPTION);
        }

        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(device)
                .sensor(sensor)
                .onValue(deviceSensorRequest.getOnValue())
                .offValue(deviceSensorRequest.getOffValue())
                .build();

        return deviceSensorRepository.save(deviceSensor)
                .toDto();
    }

    /**
     * DeviceSensor 삭제 메서드
     *
     * @param placeCode  placeCode
     * @param deviceName deviceName
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getSensorListByDevice",
                            allEntries = true
                    ),
                    @CacheEvict(
                            value = "getSensorListByDeviceName",
                            key = "#deviceName.concat(':').concat(#placeCode)"
                    ),
                    @CacheEvict(
                            value = "getSensorByDeviceAndSensor",
                            allEntries = true
                    )
            }
    )
    public void deleteSensors(String placeCode, String deviceName) {
        if (Objects.isNull(deviceSensorRepository.findByDevice_DeviceNameAndDevice_Place_PlaceCode(deviceName, placeCode))) {
            throw new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE);
        }
        deviceSensorRepository.deleteAllByDevice_Place_PlaceCodeAndDevice_DeviceName(placeCode, deviceName);
    }
}
