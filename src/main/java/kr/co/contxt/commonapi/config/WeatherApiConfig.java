package kr.co.contxt.commonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 날씨 정보 초기 데이터 설정 class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Configuration
public class WeatherApiConfig {
    /**
     * 번호 날씨 정보를 문자열 날씨 정보로 변환하기 위한 map bean
     *
     * @return the sky map
     */
    @Bean
    public Map<String, String> getSkyMap() {
        Map<String, String> skyMap = new HashMap<>();

        skyMap.put("1", "맑음");
        skyMap.put("3", "구름 많음");
        skyMap.put("4", "흐림");

        return skyMap;
    }
}
