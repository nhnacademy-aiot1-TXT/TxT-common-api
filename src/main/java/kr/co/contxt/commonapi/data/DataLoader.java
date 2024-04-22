package kr.co.contxt.commonapi.data;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DeviceRepository deviceRepository;
    private final List<Device> deviceList;

    @Override
    public void run(String... args) throws Exception {
        deviceList.stream()
                .filter(device -> !deviceRepository.existsById(device.getId()))
                .forEach(deviceRepository::save);
    }
}
