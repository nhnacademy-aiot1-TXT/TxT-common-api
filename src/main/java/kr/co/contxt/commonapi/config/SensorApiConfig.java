package kr.co.contxt.commonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SensorApiConfig {
    @Bean
    List<String> sensorList() {
        List<String> sensors = new ArrayList<>();

        sensors.add("temperature");
        sensors.add("humidity");
        sensors.add("co2");
        sensors.add("illumination");
        sensors.add("totalCount");
        sensors.add("voc");
        sensors.add("occupancy");
        sensors.add("door");

        return sensors;
    }
}
