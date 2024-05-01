package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.SensorResponse;
import lombok.*;

import javax.persistence.*;

/**
 * Sensor Entity
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorId;
    @Column(name = "sensor_name")
    private String sensorName;

    /**
     * 센서 entity를 센서 정보 dto로 변환해주는 메서드
     *
     * @return 센서 정보 dto
     */
    public SensorResponse toDto() {
        return SensorResponse.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();
    }
}
