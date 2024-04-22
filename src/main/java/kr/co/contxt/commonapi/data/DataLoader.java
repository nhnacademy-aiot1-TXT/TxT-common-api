package kr.co.contxt.commonapi.data;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final List<Device> deviceList;
    private final List<Sensor> sensorList;
    private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;

    @Override
    public void run(String... args) throws Exception {
        sensorList.stream()
                .filter(sensor -> !sensorRepository.existsBySensorName(sensor.getSensorName()))
                .forEach(sensorRepository::save);
        deviceList.stream()
                .filter(device -> !deviceRepository.existsById(device.getId()))
                .forEach(deviceRepository::save);
    }
}
