package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;

import java.util.List;

/**
 * Sensor service interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface SensorService {
    /**
     * 모든 센서를 조회하는 메서드
     *
     * @return 모든 센서 리스트
     */
    List<SensorResponse> getAllSensors();

    /**
     * 단일 센서 조회 메서드
     *
     * @param sensorId 센서 아이디
     * @return 센서 정보
     */
    SensorResponse getSensor(Long sensorId);

    /**
     * 센서 저장 메서드
     *
     * @param sensor 센서
     * @return 저장된 센서
     */
    SensorResponse saveSensor(Sensor sensor);

    /**
     * 센서 수정 메서드
     *
     * @param sensorId      센서 아이디
     * @param sensorRequest 센서 수정 요청 dto
     * @return 수정된 센서
     */
    SensorResponse updateSensor(Long sensorId, SensorRequest sensorRequest);
}
