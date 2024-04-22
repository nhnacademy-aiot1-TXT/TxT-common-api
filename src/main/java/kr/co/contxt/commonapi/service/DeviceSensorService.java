package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceSensorResponse;

import java.util.List;

public interface DeviceSensorService {
    List<DeviceSensorResponse> getSensorListByDevice(Long deviceId);

    DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId);
}
