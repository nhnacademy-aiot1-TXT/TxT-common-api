package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 날씨 온도 응답 Dto
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseDto {
    private Float temperature;
    private String sky;
}
