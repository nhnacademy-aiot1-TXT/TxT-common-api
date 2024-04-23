package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.SensorRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Sensor 정보 조회 api의 예외처리를 위한 class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = {SensorRestController.class})
public class SensorAdvice {
    /**
     * Sensor 정보 조회 중 발생하는 예외를 처리하기 위한 메서드
     *
     * @param exception 발생 예외
     * @return api 예외 응답
     */
    @ExceptionHandler(value = SensorNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> deviceNotFoundExceptionHandler(SensorNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}