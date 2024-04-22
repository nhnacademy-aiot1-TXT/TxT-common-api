package kr.co.contxt.commonapi.config;

import kr.co.contxt.commonapi.entity.Sensor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SensorApiConfig {
    @Bean
    List<Sensor> sensorList() {
        List<Sensor> sensors = new ArrayList<>();

        sensors.add(Sensor.builder().sensorName("temperature").build());
        sensors.add(Sensor.builder().sensorName("humidity").build());
        sensors.add(Sensor.builder().sensorName("co2").build());
        sensors.add(Sensor.builder().sensorName("illumination").build());
        sensors.add(Sensor.builder().sensorName("totalCount").build());
        sensors.add(Sensor.builder().sensorName("voc").build());
        sensors.add(Sensor.builder().sensorName("occupancy").build());
        sensors.add(Sensor.builder().sensorName("door").build());

        return sensors;
    }
}
