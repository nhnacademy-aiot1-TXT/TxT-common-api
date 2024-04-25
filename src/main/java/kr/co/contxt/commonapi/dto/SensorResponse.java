package kr.co.contxt.commonapi.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * Sensor 정보 응답 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Getter
@Builder
public class SensorResponse {
    private Long sensorId;
    private String sensorName;
}
