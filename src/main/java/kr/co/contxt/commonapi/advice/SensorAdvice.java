package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.SensorRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = {SensorRestController.class})
public class SensorAdvice {
    @ExceptionHandler(value = SensorNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> deviceNotFoundExceptionHandler(SensorNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}