package kr.co.contxt.commonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WeatherApiConfig {
    @Bean
    public Map<String, String> getSkyMap() {
        Map<String, String> skyMap = new HashMap<>();

        skyMap.put("1", "맑음");
        skyMap.put("3", "구름 많음");
        skyMap.put("4", "흐림");

        return skyMap;
    }
}
