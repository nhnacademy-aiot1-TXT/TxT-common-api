package kr.co.contxt.commonapi.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
