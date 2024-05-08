package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * DeviceSensor 단일 조회 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAndSensorNameDto {
    @NotBlank
    private String deviceName;
    @NotBlank
    private String sensorName;
}
