package kr.co.contxt.commonapi.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SensorResponse {
    private Long sensorId;
    private String sensorName;
}
