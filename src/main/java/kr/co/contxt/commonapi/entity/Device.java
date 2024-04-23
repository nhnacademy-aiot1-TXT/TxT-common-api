package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.DeviceResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Device Entity
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device")
public class Device {
    @Id
    @Column(name = "device_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "device_name")
    private String name;
    @Column
    LocalTime cycle;

    /**
     * To dto device response.
     *
     * @return the device response
     */
    public DeviceResponse toDto() {
        return DeviceResponse.builder()
                .deviceId(id)
                .deviceName(name)
                .cycle(cycle)
                .build();
    }
}
