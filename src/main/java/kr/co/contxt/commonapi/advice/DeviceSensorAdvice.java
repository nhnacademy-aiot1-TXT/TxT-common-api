package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.DeviceSensorRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = {DeviceSensorRestController.class})
public class DeviceSensorAdvice {
    @ExceptionHandler(value = DeviceSensorNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> deviceSensorNotFoundExceptionHandler(DeviceSensorNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}
