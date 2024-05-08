package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Sensor 정보 응답 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorResponse {
    @NotNull
    private Long sensorId;
    @NotBlank
    private String sensorName;
}
