package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.SensorNameDto;
import kr.co.contxt.commonapi.dto.TimeIntervalRequest;
import kr.co.contxt.commonapi.dto.TimeIntervalResponse;
import kr.co.contxt.commonapi.entity.TimeInterval;
import kr.co.contxt.commonapi.exception.TimeIntervalNotFoundException;
import kr.co.contxt.commonapi.repository.TimeIntervalRepository;
import kr.co.contxt.commonapi.service.TimeIntervalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TimeInterval service 구현 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class TimeIntervalServiceImpl implements TimeIntervalService {
    private final TimeIntervalRepository timeIntervalRepository;

    /**
     * 센서 아이디로 탐지 시간 조회 메서드
     *
     * @param sensorId 센서 아이디
     * @return 탐지 시간
     */
    @Override
    public TimeIntervalResponse getTimeInterval(Long sensorId) {
        return timeIntervalRepository.findBySensor_SensorId(sensorId)
                .orElseThrow(() -> new TimeIntervalNotFoundException("탐지 시간을 찾을 수 없습니다."))
                .toDto();
    }

    /**
     * 센서 이름으로 탐지 시간 조회 메서드
     *
     * @param sensorNameDto 센서 이름 dto
     * @return 탐지 시간
     */
    @Override
    public TimeIntervalResponse getTimeInterval(SensorNameDto sensorNameDto) {
        return timeIntervalRepository.findBySensor_SensorName(sensorNameDto.getSensorName())
                .orElseThrow(() -> new TimeIntervalNotFoundException("탐지 시간을 찾을 수 없습니다."))
                .toDto();
    }

    /**
     * 탐지 시간 추가 메서드
     *
     * @param timeIntervalRequest 탐지 시간 dto
     */
    @Override
    public void createTimeInterval(TimeIntervalRequest timeIntervalRequest) {
        timeIntervalRepository.save(timeIntervalRequest.toEntity());
    }

    /**
     * 탐지 시간 수정 메서드
     *
     * @param timeIntervalId      탐지 시간 아이디
     * @param timeIntervalRequest 탐지 시간 dto
     * @return 탐지 시간
     */
    @Override
    public TimeIntervalResponse updateTimeInterval(Long timeIntervalId, TimeIntervalRequest timeIntervalRequest) {
        TimeInterval timeInterval = timeIntervalRepository.findById(timeIntervalId)
                .orElseThrow(() -> new TimeIntervalNotFoundException("탐지 시간을 찾을 수 없습니다."));

        timeInterval.setBegin(timeIntervalRequest.getBegin());
        timeInterval.setEnd(timeIntervalRequest.getEnd());

        return timeIntervalRepository.save(timeInterval).toDto();
    }
}