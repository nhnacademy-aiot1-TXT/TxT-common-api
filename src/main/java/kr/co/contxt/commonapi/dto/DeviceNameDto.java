package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * DeviceSensor 리스트 조회 DTO 클래스
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceNameDto {
    @NotBlank
    private String deviceName;
}
