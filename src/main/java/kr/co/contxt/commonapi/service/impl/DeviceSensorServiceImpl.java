package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceSensorRepository;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
     * @param deviceNameDto the device name dto
     * @return deviceSensor list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getSensorListByDevice",
            key = "#deviceNameDto",
            unless = "#result == null"
    )
    public List<DeviceSensorResponse> getSensorListByDevice(DeviceNameDto deviceNameDto) {
        return deviceSensorRepository.findByDevice_DeviceName(deviceNameDto.getDeviceName())
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
            key = "#deviceId.toString().concat(#sensorId)",
            unless = "#result == null"
    )
    public DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId) {
        return deviceSensorRepository.findByDevice_DeviceIdAndSensor_SensorId(deviceId, sensorId)
                .orElseThrow(() -> new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."))
                .toDto();
    }

    /**
     * DeviceSensor 단일 조회 메서드
     *
     * @param deviceAndSensorNameDto the device and sensor name dto
     * @return deviceSensor
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getSensorByDeviceAndSensor",
            key = "#deviceAndSensorNameDto",
            unless = "#result == null"
    )
    public DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorNameDto deviceAndSensorNameDto) {
        return deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorName(deviceAndSensorNameDto.getDeviceName(), deviceAndSensorNameDto.getSensorName())
                .orElseThrow(() -> new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."))
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
    public DeviceSensorResponse updateSensorByDeviceAndSensor(DeviceSensorRequest deviceSensorRequest) {
        DeviceSensor deviceSensor = deviceSensorRepository.findByDevice_DeviceNameAndSensor_SensorName(deviceSensorRequest.getDeviceName(), deviceSensorRequest.getSensorName())
                .orElseThrow(() -> new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."));

        DeviceSensor build = deviceSensor.toBuilder()
                .onValue(deviceSensorRequest.getOnValue())
                .offValue(deviceSensorRequest.getOffValue())
                .build();

        return deviceSensorRepository.save(build).toDto();
    }
}
