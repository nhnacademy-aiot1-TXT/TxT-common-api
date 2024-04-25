package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.DeviceRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Device 조회 예외처리 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = {DeviceRestController.class})
public class DeviceAdvice {
    /**
     * DeviceNotFoundException Handler 메서드
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = DeviceNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> deviceNotFoundExceptionHandler(DeviceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}