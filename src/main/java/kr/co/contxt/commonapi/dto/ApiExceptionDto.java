package kr.co.contxt.commonapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 예외 메세지 Response 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Data
public class ApiExceptionDto {
    private final LocalDateTime time;
    private final String message;
}
