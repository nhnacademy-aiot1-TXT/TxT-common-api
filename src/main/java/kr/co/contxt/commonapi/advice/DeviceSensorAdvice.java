package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.DeviceSensorRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.exception.DeviceSensorAlreadyExistException;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import kr.co.contxt.commonapi.exception.SensorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * DeviceSensor 조회 예외처리 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = {DeviceSensorRestController.class})
public class DeviceSensorAdvice {
    /**
     * DeviceSensorNotFoundException Handler 메서드
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = {DeviceSensorNotFoundException.class, DeviceNotFoundException.class, SensorNotFoundException.class})
    public ResponseEntity<ApiExceptionDto> deviceSensorNotFoundExceptionHandler(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }

    /**
     * DeviceSensorAlreadyExistException Handler 메서드
     *
     * @param exception 발생 예외
     * @return api 예외 응답
     */
    @ExceptionHandler(value = DeviceSensorAlreadyExistException.class)
    public ResponseEntity<ApiExceptionDto> sensorAlreadyExistExceptionHandler(DeviceSensorAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}
