package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Sensor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequest {
    private String sensorName;

    public Sensor toEntity() {
        return Sensor.builder()
                .sensorName(sensorName)
                .build();
    }
}
