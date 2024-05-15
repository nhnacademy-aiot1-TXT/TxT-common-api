package kr.co.contxt.commonapi.config;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.Place;
import kr.co.contxt.commonapi.entity.Sensor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Sensor 정보 초기 데이터 설정 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Configuration
public class CommonApiConfig {
    /**
     * Sensor 리스트를 반환하는 Bean
     *
     * @return the sensor list
     */
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

    /**
     * Device 리스트를 반환하는 Bean
     *
     * @return the list
     */
    @Bean
    public List<Device> deviceList() {
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device(1L, "airconditioner", LocalTime.of(0, 10, 0)));
        deviceList.add(new Device(2L, "aircleaner", LocalTime.of(0, 20, 0)));
        deviceList.add(new Device(3L, "light", LocalTime.of(0, 30, 0)));

        return deviceList;
    }

    /**
     * Place 리스트를 반환하는 Bean
     *
     * @return the list
     */
    @Bean
    List<Place> placeList() {
        List<Place> places = new ArrayList<>();

        places.add(Place.builder().placeName("class_a").build());
        places.add(Place.builder().placeName("class_b").build());

        return places;
    }
}
