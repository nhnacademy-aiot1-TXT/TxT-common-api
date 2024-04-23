package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.WeatherResponseDto;

/**
 * 날씨 온도 서비스 interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface WeatherService {
    /**
     * 날씨 온도 조회 메서드
     *
     * @return 날씨 온도 정보
     */
    WeatherResponseDto getWeather();
}
