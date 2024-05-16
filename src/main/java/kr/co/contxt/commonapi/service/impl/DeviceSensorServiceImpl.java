package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceAndSensorAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceSensorRepository;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private static final String DEVICE_SENSOR_NOT_FOUND_MESSAGE = "장비별 센서 데이터를 찾을 수 없습니다.";

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
            value = "getSensorListByDevice",
            key = "#deviceAndPlaceNameDto.getDeviceName().concat(':').concat(#deviceAndPlaceNameDto.getPlaceName())",
            unless = "#result == null"
    )
    public List<DeviceSensorResponse> getSensorListByDevice(DeviceAndPlaceNameDto deviceAndPlaceNameDto) {
        return deviceSensorRepository.findByDevice_DeviceNameAndDevice_Place_PlaceName(
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
            value = "getSensorByDeviceAndSensor",
            key = "#deviceAndSensorAndPlaceNameDto.getDeviceName().concat(':').concat(#deviceAndSensorAndPlaceNameDto.getSensorName()).concat(':').concat(#deviceAndSensorAndPlaceNameDto.getPlaceName())",
            unless = "#result == null"
    )
    public DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto) {
        return deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceName(
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
     * @param deviceId            the device id
     * @param sensorId            the sensor id
     * @param deviceSensorRequest 장비별 센서 on/off dto
     * @return deviceSensor
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getSensorListByDevice",
                            key = "#deviceId"
                    ),
                    @CacheEvict(
                            value = "getSensorListByDevice",
                            key = "#deviceSensorRequest.getDeviceName().concat(':').concat(#deviceSensorRequest.getPlaceName())"
                    )
            },
            put = {
                    @CachePut(
                            value = "getSensorByDeviceAndSensor",
                            key = "#deviceId.toString().concat(':').concat(#sensorId)",
                            unless = "#result == null"
                    ),
                    @CachePut(
                            value = "getSensorByDeviceAndSensor",
                            key = "#deviceSensorRequest.getDeviceName().concat(':').concat(#deviceSensorRequest.getSensorName()).concat(':').concat(#deviceSensorRequest.getPlaceName())",
                            unless = "#result == null"
                    )
            }
    )
    public DeviceSensorResponse updateSensorByDeviceAndSensor(Long deviceId, Long sensorId, DeviceSensorRequest deviceSensorRequest) {
        DeviceSensor deviceSensor = deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceName(
                        deviceSensorRequest.getDeviceName(),
                        deviceSensorRequest.getSensorName(),
                        deviceSensorRequest.getPlaceName()
                )
                .orElseThrow(() -> new DeviceSensorNotFoundException(DEVICE_SENSOR_NOT_FOUND_MESSAGE));

        DeviceSensor build = deviceSensor.toBuilder()
                .onValue(deviceSensorRequest.getOnValue())
                .offValue(deviceSensorRequest.getOffValue())
                .build();

        return deviceSensorRepository.save(build).toDto();
    }
}
