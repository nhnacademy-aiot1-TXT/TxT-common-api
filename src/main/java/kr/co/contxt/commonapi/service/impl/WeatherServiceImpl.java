package kr.co.contxt.commonapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.contxt.commonapi.adapter.WeatherAdapter;
import kr.co.contxt.commonapi.dto.ApiInfo;
import kr.co.contxt.commonapi.dto.WeatherResponse;
import kr.co.contxt.commonapi.exception.SkyInfoNotFoundException;
import kr.co.contxt.commonapi.exception.TemperatureInfoNotFoundException;
import kr.co.contxt.commonapi.properties.WeatherApiProperties;
import kr.co.contxt.commonapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

/**
 * 날씨 온도 정보 조회 service 구현 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final Map<String, String> skyMap;
    private final WeatherAdapter weatherAdapter;
    private final WeatherApiProperties weatherApiProperties;

    /**
     * 기상청 초단기 예보 정보를 받아와서 날씨와 온도 정보를 파싱 후
     * dto에 담아서 응답하는 메서드
     *
     * @return 날씨 온도 정보
     */
    @Override
    public WeatherResponse getWeather() {
        LocalDateTime dateTime = LocalDateTime.now();
        if (
                dateTime.isAfter(
                        LocalDateTime.of(
                                dateTime.getYear(),
                                dateTime.getMonth(),
                                dateTime.getDayOfMonth(),
                                dateTime.getHour(),
                                0
                        )
                ) &&
                        dateTime.isBefore(
                                LocalDateTime.of(
                                        dateTime.getYear(),
                                        dateTime.getMonth(),
                                        dateTime.getDayOfMonth(),
                                        dateTime.getHour(),
                                        30
                                )
                        )
        ) {
            dateTime = dateTime.minusMinutes(30);
        }

        String weatherInfo = weatherAdapter.getWeatherInfo(
                weatherApiProperties.getServiceKey(),
                weatherApiProperties.getPageNo(),
                weatherApiProperties.getNumOfRows(),
                weatherApiProperties.getDataType(),
                dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                dateTime.format(DateTimeFormatter.ofPattern("HH30")),
                weatherApiProperties.getNx(),
                weatherApiProperties.getNy()
        );

        JSONArray jsonArray = new JSONObject(weatherInfo).getJSONObject("response")
                .getJSONObject("body")
                .getJSONObject("items")
                .getJSONArray("item");

        ObjectMapper objectMapper = new ObjectMapper();
        ApiInfo[] apiInfos;
        try {
            apiInfos = objectMapper.readValue(jsonArray.toString(), ApiInfo[].class);
        } catch (JsonProcessingException e) {
            apiInfos = new ApiInfo[]{};
        }

        ApiInfo temperatureInfo = Arrays.stream(apiInfos)
                .filter(apiInfo -> "T1H".equals(apiInfo.getCategory()))
                .findFirst()
                .orElseThrow(() -> new TemperatureInfoNotFoundException("온도를 가져올 수 없습니다."));
        ApiInfo skyInfo = Arrays.stream(apiInfos)
                .filter(apiInfo -> "SKY".equals(apiInfo.getCategory()))
                .findFirst()
                .orElseThrow(() -> new SkyInfoNotFoundException("날씨를 가져올 수 없습니다."));

        return new WeatherResponse(Float.valueOf(temperatureInfo.getFcstValue()), skyMap.get(skyInfo.getFcstValue()));
    }
}
