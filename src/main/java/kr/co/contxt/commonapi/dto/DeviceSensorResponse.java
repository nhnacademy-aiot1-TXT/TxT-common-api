package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DeviceSensor 응답 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSensorResponse {
    private Long deviceId;
    private Long sensorId;
    private String sensorName;
    private Float onValue;
    private Float offValue;
}
