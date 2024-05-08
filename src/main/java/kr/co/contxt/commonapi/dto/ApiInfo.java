package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 기상청에서 가져온 json String의 item mapping 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo {
    @NotBlank
    private String baseDate;
    @NotBlank
    private String baseTime;
    @NotBlank
    private String category;
    @NotBlank
    private String fcstDate;
    @NotBlank
    private String fcstTime;
    @NotBlank
    private String fcstValue;
    @NotNull
    private Integer nx;
    @NotNull
    private Integer ny;
}
