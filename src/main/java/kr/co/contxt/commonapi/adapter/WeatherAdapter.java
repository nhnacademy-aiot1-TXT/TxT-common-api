package kr.co.contxt.commonapi.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 기상청 초단기 예보 조회를 위한 Adapter class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@FeignClient(name = "weather-api", url = "http://apis.data.go.kr", path = "/1360000/VilageFcstInfoService_2.0")
public interface WeatherAdapter {
    /**
     * 초단기 예보 조회 메서드
     *
     * @param serviceKey 인증에 필요한 서비스 키
     * @param pageNo     페이지 번호
     * @param numOfRows  행의 갯수
     * @param dataType   데이터 타입
     * @param date       the date
     * @param time       the time
     * @param nx         경도
     * @param ny         위도
     * @return 초단기 예보 정보 json String
     */
    @GetMapping("/getUltraSrtFcst")
    String getWeatherInfo(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("pageNo") String pageNo,
            @RequestParam("numOfRows") String numOfRows,
            @RequestParam("dataType") String dataType,
            @RequestParam("base_date") String date,
            @RequestParam("base_time") String time,
            @RequestParam("nx") String nx,
            @RequestParam("ny") String ny
    );
}
