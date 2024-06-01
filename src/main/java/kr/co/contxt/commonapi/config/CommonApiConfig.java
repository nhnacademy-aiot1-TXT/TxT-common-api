package kr.co.contxt.commonapi.config;

import kr.co.contxt.commonapi.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Sensor 정보 초기 데이터 설정 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Configuration
public class CommonApiConfig {
    private static final Long ID_INITIAL_VALUE = 1L;

    /**
     * Sensor 리스트를 반환하는 Bean
     *
     * @return the sensor list
     */
    @Bean
    List<Sensor> sensorList() {
        List<Sensor> sensors = new ArrayList<>();

        AtomicLong id = new AtomicLong(ID_INITIAL_VALUE);
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("temperature").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("humidity").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("co2").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("illumination").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("total_people_count").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("voc").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("occupancy").build());
        sensors.add(Sensor.builder().sensorId(id.getAndIncrement()).sensorName("door").build());

        return sensors;
    }

    /**
     * TimeInterval 리스트를 반환하는 Bean
     *
     * @return the timeInterval list
     */
    @Bean
    List<TimeInterval> timeIntervalList() {
        Sensor sensor = Sensor.builder()
                .sensorId(7L)
                .sensorName("occupancy")
                .build();

        List<TimeInterval> timeIntervalList = new ArrayList<>();

        timeIntervalList.add(TimeInterval.builder().begin(LocalTime.of(2, 0)).end(LocalTime.of(8, 0)).sensor(sensor).build());

        return timeIntervalList;
    }

    /**
     * Device 리스트를 반환하는 Bean
     *
     * @return the device list
     */
    @Bean
    public List<Device> deviceList(List<Place> placeList) {
        List<Device> deviceList = new ArrayList<>();

        AtomicLong id = new AtomicLong(ID_INITIAL_VALUE);
        placeList.forEach(place -> {
            deviceList.add(Device.builder().deviceId(id.getAndIncrement()).place(place).deviceName("airconditioner").aiMode(1).build());
            deviceList.add(Device.builder().deviceId(id.getAndIncrement()).place(place).deviceName("aircleaner").aiMode(0).build());
            deviceList.add(Device.builder().deviceId(id.getAndIncrement()).place(place).deviceName("light").aiMode(0).build());
        });

        return deviceList;
    }

    /**
     * Place 리스트를 반환하는 Bean
     *
     * @return the place list
     */
    @Bean
    List<Place> placeList() {
        List<Place> placeList = new ArrayList<>();

        AtomicLong id = new AtomicLong(ID_INITIAL_VALUE);
        placeList.add(Place.builder().placeId(id.getAndIncrement()).placeName("강의실 A").placeCode("class_a").cycle(LocalTime.of(0, 10, 0)).build());
        placeList.add(Place.builder().placeId(id.getAndIncrement()).placeName("강의실 B").placeCode("class_b").cycle(LocalTime.of(0, 15, 0)).build());

        return placeList;
    }

    /**
     * DeviceSensor 리스트를 반환하는 Bean
     *
     * @return the deviceSensor list
     */
    @Bean
    List<DeviceSensor> deviceSensorList() {
        Device airConA = Device.builder().deviceId(1L).build();
        Device airConB = Device.builder().deviceId(4L).build();
        Device airCleanA = Device.builder().deviceId(2L).build();
        Device airCleanB = Device.builder().deviceId(5L).build();
        Sensor temp = Sensor.builder().sensorId(1L).build();
        Sensor voc = Sensor.builder().sensorId(6L).build();

        List<DeviceSensor> deviceSensorList = new ArrayList<>();

        deviceSensorList.add(DeviceSensor.builder().offValue(19F).onValue(26F).device(airConA).sensor(temp).build());
        deviceSensorList.add(DeviceSensor.builder().offValue(200F).onValue(400F).device(airCleanA).sensor(voc).build());
        deviceSensorList.add(DeviceSensor.builder().offValue(19F).onValue(26F).device(airConB).sensor(temp).build());
        deviceSensorList.add(DeviceSensor.builder().offValue(200F).onValue(400F).device(airCleanB).sensor(voc).build());

        return deviceSensorList;
    }
}
