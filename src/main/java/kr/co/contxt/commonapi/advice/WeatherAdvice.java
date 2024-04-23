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
 * 날씨 조회 api의 예외처리를 하기 위한 advice class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = WeatherRestController.class)
public class WeatherAdvice {
    /**
     * 날씨 데이터와 온도 데이터 조회 중 예외가 발생하는 것을 처리하기 위한 메서드
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
