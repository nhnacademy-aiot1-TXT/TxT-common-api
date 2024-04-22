package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest {
    String deviceName;
    LocalTime cycle;

    public Device toEntity() {
        return Device.builder()
                .name(deviceName)
                .cycle(cycle)
                .build();
    }
}
