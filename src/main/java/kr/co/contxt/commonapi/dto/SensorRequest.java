package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Sensor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 센서 정보 요청 dto class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequest {
    private String sensorName;

    /**
     * dto를 entity로 변환하는 메서드
     *
     * @return 센서 entity
     */
    public Sensor toEntity() {
        return Sensor.builder()
                .sensorName(sensorName)
                .build();
    }
}
