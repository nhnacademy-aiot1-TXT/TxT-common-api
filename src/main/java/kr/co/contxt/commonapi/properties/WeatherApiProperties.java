package kr.co.contxt.commonapi.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 날씨 데이터를 기상청 open api에서 가져오기 위해 필요한 정보들을 가지고 있는 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "weather.api")
public class WeatherApiProperties {
    private String serviceKey;
    private String pageNo;
    private String numOfRows;
    private String dataType;
    private String nx;
    private String ny;
}
