package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 장비별 센서 on/off dto
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSensorRequest {
    @NotBlank
    private String deviceName;
    @NotBlank
    private String sensorName;
    @NotNull
    private Float onValue;
    @NotNull
    private Float offValue;
}
