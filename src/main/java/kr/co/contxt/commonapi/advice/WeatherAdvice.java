package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.WeatherRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.SkyInfoNotFoundException;
import kr.co.contxt.commonapi.exception.TemperatureInfoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Weather 조회 예외처리 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = WeatherRestController.class)
public class WeatherAdvice {
    /**
     * TemperatureInfoNotFoundException, SkyInfoNotFoundException Handler 메서드
     *
     * @param exception 발생 예외
     * @return api 예외 응답
     */
    @ExceptionHandler(value = {TemperatureInfoNotFoundException.class, SkyInfoNotFoundException.class})
    public ResponseEntity<ApiExceptionDto> weatherExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}
