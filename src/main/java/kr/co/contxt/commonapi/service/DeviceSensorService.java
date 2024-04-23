package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;

import java.util.List;

public interface DeviceSensorService {
    List<DeviceSensorResponse> getSensorListByDevice(Long deviceId);

    List<DeviceSensorResponse> getSensorListByDevice(DeviceNameDto deviceNameDto);

    DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId);

    DeviceSensorResponse getSensorByDeviceAndSensor(DeviceAndSensorNameDto deviceAndSensorNameDto);
}
