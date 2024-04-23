package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.repository.SensorRepository;
import kr.co.contxt.commonapi.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SensorResponse> getAllSensors() {
        return sensorRepository.findAll()
                .stream()
                .map(Sensor::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SensorResponse getSensor(Long sensorId) {
        return sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException("Sensor를 찾을 수 없습니다."))
                .toDto();
    }

    @Override
    @Transactional
    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    @Transactional
    public Sensor updateSensor(Long sensorId, SensorRequest sensorRequest) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException("Sensor를 찾을 수 없습니다."));

        sensor.setSensorName(sensorRequest.getSensorName());

        return sensorRepository.save(sensor);
    }
}
