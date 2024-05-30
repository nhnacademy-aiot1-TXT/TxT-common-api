package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.SensorAlreadyExistException;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import kr.co.contxt.commonapi.repository.SensorRepository;
import kr.co.contxt.commonapi.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    private static final String SENSOR_NOT_FOUND_MESSAGE = "센서를 찾을 수 없습니다.";
    private static final String SENSOR_ALREADY_EXIST_EXCEPTION_MESSAGE = "센서가 이미 존재합니다.";

    /**
     * Sensor 리스트 조회 메서드
     *
     * @return sensor list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getAllSensors",
            key = "'all'",
            unless = "#result == null"
    )
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
    @Cacheable(
            value = "getSensor",
            key = "#sensorId",
            unless = "#result == null"
    )
    public SensorResponse getSensor(Long sensorId) {
        return sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException(SENSOR_NOT_FOUND_MESSAGE))
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
    @CacheEvict(
            value = "getAllSensors",
            key = "'all'"
    )
    public SensorResponse saveSensor(Sensor sensor) {
        if (sensorRepository.existsBySensorName(sensor.getSensorName()))
            throw new SensorAlreadyExistException(SENSOR_ALREADY_EXIST_EXCEPTION_MESSAGE);

        return sensorRepository.save(sensor)
                .toDto();
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
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getAllSensors",
                            key = "'all'"
                    )
            },
            put = {
                    @CachePut(
                            value = "getSensor",
                            key = "#sensorId",
                            unless = "#result == null"
                    )
            }
    )
    public SensorResponse updateSensor(Long sensorId, SensorRequest sensorRequest) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException(SENSOR_NOT_FOUND_MESSAGE));

        sensor.setSensorName(sensorRequest.getSensorName());

        return sensorRepository.save(sensor)
                .toDto();
    }
}
