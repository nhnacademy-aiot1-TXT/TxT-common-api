package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.adaptor.WeatherAdaptor;
import kr.co.contxt.commonapi.dto.WeatherResponseDto;
import kr.co.contxt.commonapi.properties.WeatherApiProperties;
import kr.co.contxt.commonapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final Map<String, String> skyMap;
    private final WeatherAdaptor weatherAdaptor;
    private final WeatherApiProperties weatherApiProperties;

    @Override
    public WeatherResponseDto getWeather() {
        LocalDateTime dateTime = LocalDateTime.now().minusHours(1);
        String weatherInfo = weatherAdaptor.getWeatherInfo(
                weatherApiProperties.getServiceKey(),
                weatherApiProperties.getPageNo(),
                weatherApiProperties.getNumOfRows(),
                weatherApiProperties.getDataType(),
                dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                dateTime.format(DateTimeFormatter.ofPattern("HHmm")),
                weatherApiProperties.getNx(),
                weatherApiProperties.getNy()
        );

        JSONArray jsonArray = new JSONObject(weatherInfo).getJSONObject("response")
                .getJSONObject("body")
                .getJSONObject("items")
                .getJSONArray("item");
        JSONObject temperatureInfo = jsonArray.getJSONObject(0);
        JSONObject skyInfo = jsonArray.getJSONObject(5);

        return new WeatherResponseDto(temperatureInfo.getFloat("fcstValue"), skyMap.get(skyInfo.getString("fcstValue")));
    }
}
