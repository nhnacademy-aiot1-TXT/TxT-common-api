package kr.co.contxt.commonapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 예외 메세지 Response 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Data
public class ApiExceptionDto {
    @NotNull
    private final LocalDateTime time;
    @NotBlank
    private final String message;
}
