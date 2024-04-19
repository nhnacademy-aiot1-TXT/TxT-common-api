package kr.co.contxt.commonapi.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-api", url = "http://apis.data.go.kr", path = "/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
public interface WeatherAdaptor {
    @GetMapping
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
