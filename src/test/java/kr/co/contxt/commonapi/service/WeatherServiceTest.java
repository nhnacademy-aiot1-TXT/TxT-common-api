package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.adapter.WeatherAdapter;
import kr.co.contxt.commonapi.dto.WeatherResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;
    @MockBean
    private WeatherAdapter weatherAdapter;

    @Test
    void getWeather() {
        // Mock 데이터 설정
        String mockWeatherInfo = "{\"response\":{\"body\":{\"items\":{\"item\":[\n" +
                "          {\n" +
                "            \"baseDate\": \"20240419\",\n" +
                "            \"baseTime\": \"1630\",\n" +
                "            \"category\": \"SKY\",\n" +
                "            \"fcstDate\": \"20240419\",\n" +
                "            \"fcstTime\": \"1700\",\n" +
                "            \"fcstValue\": \"1\",\n" +
                "            \"nx\": 55,\n" +
                "            \"ny\": 127\n" +
                "          },\n" +
                "          {\n" +
                "            \"baseDate\": \"20240419\",\n" +
                "            \"baseTime\": \"1630\",\n" +
                "            \"category\": \"T1H\",\n" +
                "            \"fcstDate\": \"20240419\",\n" +
                "            \"fcstTime\": \"1800\",\n" +
                "            \"fcstValue\": \"25.5\",\n" +
                "            \"nx\": 55,\n" +
                "            \"ny\": 127\n" +
                "          }\n" +
                "]}}}}";
        given(weatherAdapter.getWeatherInfo(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).willReturn(mockWeatherInfo);

        // 테스트 실행
        WeatherResponseDto result = weatherService.getWeather();

        // 결과 검증
        assertEquals(25.5f, result.getTemperature());
        assertEquals("맑음", result.getSky());
    }
}