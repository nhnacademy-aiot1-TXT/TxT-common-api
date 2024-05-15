package kr.co.contxt.commonapi.data;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.Place;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.repository.PlaceRepository;
import kr.co.contxt.commonapi.repository.SensorRepository;
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
    private final List<Device> deviceList;
    private final List<Sensor> sensorList;
    private final List<Place> placeList;
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;
    private final PlaceRepository placeRepository;

    @Override
    public void run(String... args) {
        sensorList.stream()
                .filter(sensor -> !sensorRepository.existsBySensorName(sensor.getSensorName()))
                .forEach(sensorRepository::save);
        deviceList.stream()
                .filter(device -> !deviceRepository.existsById(device.getDeviceId()))
                .forEach(deviceRepository::save);
        placeList.stream()
                .filter(place -> !placeRepository.existsByPlaceName(place.getPlaceName()))
                .forEach(placeRepository::save);
    }
}
