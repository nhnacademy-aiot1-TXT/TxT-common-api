package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;

import java.util.List;

public interface SensorService {
    List<SensorResponse> getAllSensors();

    SensorResponse getSensor(Long sensorId);

    Sensor saveSensor(Sensor sensor);

    Sensor updateSensor(Long sensorId, SensorRequest sensorRequest);
}
