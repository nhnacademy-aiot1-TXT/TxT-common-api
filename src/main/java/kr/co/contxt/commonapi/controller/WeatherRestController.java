package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.dto.WeatherResponseDto;
import kr.co.contxt.commonapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/weather")
public class WeatherRestController {
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponseDto> getWeather() {
        WeatherResponseDto weather = weatherService.getWeather();

        return ResponseEntity.ok(weather);
    }
}