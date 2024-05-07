package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.TimeIntervalResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * TimeInterval Entity
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
@Table(name = "time_interval")
public class TimeInterval {
    @Id
    @Column(name = "time_interval_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeIntervalId;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    private LocalTime begin;
    private LocalTime end;

    /**
     * To dto time interval response.
     *
     * @return the time interval response
     */
    public TimeIntervalResponse toDto() {
        return TimeIntervalResponse.builder()
                .sensorId(sensor.getSensorId())
                .sensorName(sensor.getSensorName())
                .begin(begin)
                .end(end)
                .build();
    }
}
