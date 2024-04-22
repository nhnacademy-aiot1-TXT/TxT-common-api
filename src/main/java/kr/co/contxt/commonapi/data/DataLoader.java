package kr.co.contxt.commonapi.data;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DeviceRepository deviceRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!deviceRepository.existsById(1L)) {
            deviceRepository.save(new Device(1L, "airconditioner", LocalTime.of(0, 10, 0)));
        }
        if (!deviceRepository.existsById(2L)) {
            deviceRepository.save(new Device(2L, "aircleaner", LocalTime.of(0, 10, 0)));
        }
        if (!deviceRepository.existsById(3L)) {
            deviceRepository.save(new Device(3L, "light", LocalTime.of(0, 10, 0)));
        }
    }
}
