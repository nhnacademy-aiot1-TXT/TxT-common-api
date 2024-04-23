package kr.co.contxt.commonapi.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 센서 정보 응답 dto class
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
