package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String sensorName;
    private LocalTime begin;
    private LocalTime end;
}
