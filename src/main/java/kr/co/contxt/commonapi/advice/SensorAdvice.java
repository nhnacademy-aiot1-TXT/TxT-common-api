package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.SensorRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.SensorAlreadyExistException;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Sensor 조회 예외처리 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = {SensorRestController.class})
public class SensorAdvice {
    /**
     * SensorNotFoundException Handler 메서드
     *
     * @param exception 발생 예외
     * @return api 예외 응답
     */
    @ExceptionHandler(value = SensorNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> sensorNotFoundExceptionHandler(SensorNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }

    /**
     * SensorAlreadyExistException Handler 메서드
     *
     * @param exception 발생 예외
     * @return api 예외 응답
     */
    @ExceptionHandler(value = SensorAlreadyExistException.class)
    public ResponseEntity<ApiExceptionDto> sensorAlreadyExistExceptionHandler(SensorAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}