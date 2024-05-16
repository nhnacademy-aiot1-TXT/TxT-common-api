package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Device 생성, 수정 DTO 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {
    @NotNull
    Long placeId;
    @NotBlank
    String deviceName;
    @NotNull
    Integer aiMode;

    /**
     * To entity device.
     *
     * @return the device
     */
    public Device toEntity() {
        return Device.builder()
                .place(new Place(placeId, null, null))
                .deviceName(deviceName)
                .aiMode(aiMode)
                .build();
    }
}
