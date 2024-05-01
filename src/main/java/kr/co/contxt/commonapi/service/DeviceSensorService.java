package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;

import java.util.List;

/**
 * DeviceSensor Service interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface DeviceSensorService {
    /**
     * Device Id로 리스트 조회 메서드
     *
     * @param deviceId the device id
     * @return the deviceSensor list
     */
    List<DeviceSensorResponse> getSensorListByDevice(Long deviceId);

    /**
     * Device Name으로 리스트 조회 메서드
     *
     * @param deviceNameDto the device name dto
     * @return the deviceSensor list
     */
    List<DeviceSensorResponse> getSensorListByDevice(DeviceNameDto deviceNameDto);

    /**
     * Device Id, Sensor Id로 단일 조회 메서드
     *
     * @param deviceId the device id
     * @param sensorId the sensor id
     * @return the deviceSensor
     */
    DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId);

    /**
     * Device Name, Sensor Name으로 단일 조회 메서드
     *
     * @param deviceAndSensorNameDto the device and sensor name dto
     * @return the deviceSensor
     */
    DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorNameDto deviceAndSensorNameDto);
}
