package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

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
    @NotBlank
    String deviceName;
    @NotNull
    LocalTime cycle;

    /**
     * To entity device.
     *
     * @return the device
     */
    public Device toEntity() {
        return Device.builder()
                .deviceName(deviceName)
                .cycle(cycle)
                .build();
    }
}
