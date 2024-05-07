package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.SensorNameDto;
import kr.co.contxt.commonapi.dto.TimeIntervalRequest;
import kr.co.contxt.commonapi.dto.TimeIntervalResponse;

/**
 * TimeInterval Service interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface TimeIntervalService {
    /**
     * 센서 아이디로 탐지 시간 조회 메서드
     *
     * @param sensorId 센서 아이디
     * @return 탐지 시간
     */
    TimeIntervalResponse getTimeInterval(Long sensorId);

    /**
     * 센서 이름으로 탐지 시간 조회 메서드
     *
     * @param sensorNameDto the sensor name dto
     * @return 탐지 시간
     */
    TimeIntervalResponse getTimeInterval(SensorNameDto sensorNameDto);

    /**
     * 탐지 시간 추가 메서드
     *
     * @param timeIntervalRequest 탐지 시간 dto
     */
    void createTimeInterval(TimeIntervalRequest timeIntervalRequest);

    /**
     * 탐지 시간 수정 메서드
     *
     * @param timeIntervalId      센서 아이디
     * @param timeIntervalRequest 탐지 시간 dto
     * @return 탐지 시간
     */
    TimeIntervalResponse updateTimeInterval(Long timeIntervalId, TimeIntervalRequest timeIntervalRequest);
}
