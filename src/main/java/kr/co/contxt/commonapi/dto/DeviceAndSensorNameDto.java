package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceAndSensorNameDto {
    private String deviceName;
    private String sensorName;
}
