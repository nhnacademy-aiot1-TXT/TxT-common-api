package kr.co.contxt.commonapi.data;

import kr.co.contxt.commonapi.entity.*;
import kr.co.contxt.commonapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 데이터 세팅 class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final List<Sensor> sensorList;
    private final List<TimeInterval> timeIntervalList;
    private final List<Place> placeList;
    private final List<Device> deviceList;
    private final List<DeviceSensor> deviceSensorList;
    private final SensorRepository sensorRepository;
    private final TimeIntervalRepository timeIntervalRepository;
    private final PlaceRepository placeRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceSensorRepository deviceSensorRepository;

    @Override
    public void run(String... args) {
        sensorList.stream()
                .filter(sensor -> !sensorRepository.existsBySensorName(sensor.getSensorName()))
                .forEach(sensorRepository::save);
        timeIntervalList.stream()
                .filter(timeInterval -> !timeIntervalRepository.existsBySensor_SensorId(timeInterval.getSensor().getSensorId()))
                .forEach(timeIntervalRepository::save);
        placeList.stream()
                .filter(place -> !placeRepository.existsByPlaceName(place.getPlaceName()))
                .forEach(placeRepository::save);
        deviceList.stream()
                .filter(device -> !deviceRepository.existsById(device.getDeviceId()))
                .forEach(deviceRepository::save);
        deviceSensorList.stream()
                .filter(deviceSensor -> !deviceSensorRepository.existsByDevice_DeviceIdAndSensor_SensorId(deviceSensor.getDevice().getDeviceId(), deviceSensor.getSensor().getSensorId()))
                .forEach(deviceSensorRepository::save);
    }
}
