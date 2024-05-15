package kr.co.contxt.commonapi.advice;

import kr.co.contxt.commonapi.controller.PlaceRestController;
import kr.co.contxt.commonapi.dto.ApiExceptionDto;
import kr.co.contxt.commonapi.exception.PlaceNotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Place 조회 예외처리 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestControllerAdvice(basePackageClasses = {PlaceRestController.class})
public class PlaceAdvice {
    /**
     * PlaceNotFountException Handler 메서드
     *
     * @param exception 발생 예외
     * @return api 예외 응답
     */
    @ExceptionHandler(value = PlaceNotFountException.class)
    public ResponseEntity<ApiExceptionDto> placeNotFoundExceptionHandler(PlaceNotFountException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiExceptionDto(LocalDateTime.now(), exception.getMessage()));
    }
}
