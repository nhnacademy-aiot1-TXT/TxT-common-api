package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceSensorRepository;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
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
    public List<DeviceSensorResponse> getSensorListByDevice(Long deviceId) {
        return deviceSensorRepository.findByDevice_Id(deviceId)
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
    public List<DeviceSensorResponse> getSensorListByDevice(DeviceNameDto deviceNameDto) {
        return deviceSensorRepository.findByDevice_Name(deviceNameDto.getDeviceName())
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
    public DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId) {
        return deviceSensorRepository.findByDevice_IdAndSensor_SensorId(deviceId, sensorId)
                .map(DeviceSensor::toDto)
                .orElseThrow(() -> new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."));
    }

    /**
     * DeviceSensor 단일 조회 메서드
     *
     * @param deviceAndSensorNameDto the device and sensor name dto
     * @return deviceSensor
     */
    @Override
    public DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorNameDto deviceAndSensorNameDto) {
        return deviceSensorRepository.findByDevice_NameAndSensor_SensorName(deviceAndSensorNameDto.getDeviceName(), deviceAndSensorNameDto.getSensorName())
                .map(DeviceSensor::toDto)
                .orElseThrow(() -> new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."));
    }
}
