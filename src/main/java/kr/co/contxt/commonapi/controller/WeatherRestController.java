package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Weather Rest Controller", description = "기상청 초단기 예보 API")
public class WeatherRestController {
    private final WeatherService weatherService;

    @GetMapping
    @Operation(summary = "날씨,온도 조회 API")
    public ResponseEntity<WeatherResponseDto> getWeather() {
        WeatherResponseDto weather = weatherService.getWeather();

        return ResponseEntity.ok(weather);
    }
}
