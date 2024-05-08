package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * TimeInterval 응답 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeIntervalResponse {
    @NotNull
    private Long sensorId;
    @NotBlank
    private String sensorName;
    @NotNull
    private LocalTime begin;
    @NotNull
    private LocalTime end;
}
