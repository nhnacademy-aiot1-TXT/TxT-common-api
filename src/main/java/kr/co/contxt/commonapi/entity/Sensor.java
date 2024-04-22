package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.SensorResponse;
import lombok.*;

import javax.persistence.*;

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

    public SensorResponse toDto() {
        return SensorResponse.builder()
                .sensorId(sensorId)
                .sensorName(sensorName)
                .build();
    }
}
