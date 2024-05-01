package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.TimeIntervalRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.TimeIntervalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * TimeIntervalRestController 조회 예외처리 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = TimeIntervalRestController.class)
public class TimeIntervalAdvice {
    /**
     * TimeIntervalNotFoundException Handler 메서드
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = TimeIntervalNotFoundException.class)
    public ResponseEntity<ApiExceptionDto> timeIntervalNotFoundExceptionHandler(TimeIntervalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}
