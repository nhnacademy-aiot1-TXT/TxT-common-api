package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_sensor")
public class DeviceSensor {
    @Id
    @Column(name = "device_sensor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceSensorId;
    @Column(name = "on_value")
    private Float onValue;
    @Column(name = "off_value")
    private Float offValue;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    public DeviceSensorResponse toDto() {
        return DeviceSensorResponse.builder()
                .onValue(onValue)
                .offValue(offValue)
                .build();
    }
}
