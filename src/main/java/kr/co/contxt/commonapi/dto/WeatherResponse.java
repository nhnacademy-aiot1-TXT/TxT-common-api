package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Weather 응답 Dto 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private Float temperature;
    private String sky;
}
