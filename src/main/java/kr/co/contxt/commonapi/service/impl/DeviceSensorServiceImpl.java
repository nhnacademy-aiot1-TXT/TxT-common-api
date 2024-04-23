package kr.co.contxt.commonapi.service.impl;

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

@Service
@RequiredArgsConstructor
public class DeviceSensorServiceImpl implements DeviceSensorService {
    private final DeviceSensorRepository deviceSensorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DeviceSensorResponse> getSensorListByDevice(Long deviceId) {
        return deviceSensorRepository.findByDevice_Id(deviceId)
                .stream()
                .map(DeviceSensor::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DeviceSensorResponse getSensorByDeviceAndSensor(Long deviceId, Long sensorId) {
        return deviceSensorRepository.findByDevice_IdAndSensor_SensorId(deviceId, sensorId)
                .map(DeviceSensor::toDto)
                .orElseThrow(() -> new DeviceSensorNotFoundException("장비별 센서 데이터를 찾을 수 없습니다."));
    }
}
