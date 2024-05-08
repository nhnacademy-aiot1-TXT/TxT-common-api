package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Long deviceId;
    @NotNull
    private Long sensorId;
    @NotBlank
    private String sensorName;
    @NotNull
    private Float onValue;
    @NotNull
    private Float offValue;
}
