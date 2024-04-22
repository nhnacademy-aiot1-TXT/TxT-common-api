package kr.co.contxt.commonapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiExceptionDto {
    private final LocalDateTime time;
    private final String message;
}
