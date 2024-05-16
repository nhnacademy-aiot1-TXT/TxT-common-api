package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
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
     * @param deviceNameAndPlaceNameDto the device name dto
     * @return the deviceSensor list
     */
    List<DeviceSensorResponse> getSensorListByDevice(DeviceNameAndPlaceNameDto deviceNameAndPlaceNameDto);

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
     * @param deviceAndSensorNameAndPlaceNameDto the device and sensor name dto
     * @return the deviceSensor
     */
    DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorNameAndPlaceNameDto deviceAndSensorNameAndPlaceNameDto);

    /**
     * Device id, Sensor id로 업데이트 메서드
     *
     * @param deviceId            the device id
     * @param sensorId            the sensor id
     * @param deviceSensorRequest 장비별 센서 on/off dto
     * @return the deviceSensor
     */
    DeviceSensorResponse updateSensorByDeviceAndSensor(Long deviceId, Long sensorId, DeviceSensorRequest deviceSensorRequest);
}
