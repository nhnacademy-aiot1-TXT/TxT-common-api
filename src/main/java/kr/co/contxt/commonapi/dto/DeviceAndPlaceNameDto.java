package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * DeviceSensor 리스트 조회 DTO 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAndPlaceNameDto {
    @NotBlank
    private String deviceName;
    @NotBlank
    private String placeName;
}
