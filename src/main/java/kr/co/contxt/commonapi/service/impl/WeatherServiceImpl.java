package kr.co.contxt.commonapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.contxt.commonapi.adapter.WeatherAdapter;
import kr.co.contxt.commonapi.dto.ApiInfo;
import kr.co.contxt.commonapi.dto.WeatherResponseDto;
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

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final Map<String, String> skyMap;
    private final WeatherAdapter weatherAdapter;
    private final WeatherApiProperties weatherApiProperties;

    @Override
    public WeatherResponseDto getWeather() {
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
            throw new RuntimeException(e);
        }

        ApiInfo temperatureInfo = Arrays.stream(apiInfos).filter(apiInfo -> "T1H".equals(apiInfo.getCategory())).findFirst().orElse(null);
        ApiInfo skyInfo = Arrays.stream(apiInfos).filter(apiInfo -> "SKY".equals(apiInfo.getCategory())).findFirst().orElse(null);

        return new WeatherResponseDto(Float.valueOf(temperatureInfo.getFcstValue()), skyMap.get(skyInfo.getFcstValue()));
    }
}
