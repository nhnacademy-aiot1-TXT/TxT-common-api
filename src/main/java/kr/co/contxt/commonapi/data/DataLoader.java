package kr.co.contxt.commonapi.data;

import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final List<String> sensorList;
    private final SensorRepository sensorRepository;

    @Override
    public void run(String... args) throws Exception {
        sensorList.stream()
                .filter(s -> !sensorRepository.existsBySensorName(s))
                .forEach(s -> sensorRepository.save(
                        Sensor.builder()
                                .sensorName(s)
                                .build())
                );
    }
}
