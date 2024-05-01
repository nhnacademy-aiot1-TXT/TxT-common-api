package kr.co.contxt.commonapi.dto;

import lombok.Data;

/**
 * 기상청에서 가져온 json String의 item mapping 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
public class ApiInfo {
    private String baseDate;
    private String baseTime;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private Integer nx;
    private Integer ny;
}
