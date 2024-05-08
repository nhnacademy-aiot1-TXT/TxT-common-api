package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.entity.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * TimeInterval 요청 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeIntervalRequest {
    @NotNull
    private Long sensorId;
    @NotBlank
    private String sensorName;
    @NotNull
    private LocalTime begin;
    @NotNull
    private LocalTime end;

    /**
     * To entity time interval.
     *
     * @return the time interval
     */
    public TimeInterval toEntity() {
        return TimeInterval.builder()
                .sensor(new Sensor(sensorId, sensorName))
                .begin(begin)
                .end(end)
                .build();
    }
}
