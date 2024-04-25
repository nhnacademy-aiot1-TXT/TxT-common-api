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

/**
 * Sensor service 구현 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;

    /**
     * Sensor 리스트 조회 메서드
     *
     * @return sensor list
     */
    @Override
    @Transactional(readOnly = true)
    public List<SensorResponse> getAllSensors() {
        return sensorRepository.findAll()
                .stream()
                .map(Sensor::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Sensor 단일 조회 메서드
     *
     * @param sensorId 센서 아이디
     * @return sensor
     */
    @Override
    @Transactional(readOnly = true)
    public SensorResponse getSensor(Long sensorId) {
        return sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException("Sensor를 찾을 수 없습니다."))
                .toDto();
    }

    /**
     * Sensor 저장 메서드
     *
     * @param sensor 센서
     * @return 저장된 센서
     */
    @Override
    @Transactional
    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    /**
     * Sensor 수정 메서드
     *
     * @param sensorId      센서 아이디
     * @param sensorRequest 센서 수정 요청 dto
     * @return 수정된 센서
     */
    @Override
    @Transactional
    public Sensor updateSensor(Long sensorId, SensorRequest sensorRequest) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException("Sensor를 찾을 수 없습니다."));

        sensor.setSensorName(sensorRequest.getSensorName());

        return sensorRepository.save(sensor);
    }
}
