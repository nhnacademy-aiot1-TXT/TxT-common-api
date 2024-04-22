package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.DeviceRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackageClasses = {DeviceRestController.class})
public class DeviceAdvice {
    @ExceptionHandler(value = DeviceNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> deviceNotFoundExceptionHandler(DeviceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}