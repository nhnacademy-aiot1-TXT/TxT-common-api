package kr.co.contxt.commonapi.config;

import kr.co.contxt.commonapi.entity.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeviceApiConfig {
    @Bean
    public List<Device> deviceList() {
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(new Device(1L, "airconditioner", LocalTime.of(0, 10, 0)));
        deviceList.add(new Device(2L, "aircleaner", LocalTime.of(0, 20, 0)));
        deviceList.add(new Device(3L, "light", LocalTime.of(0, 30, 0)));

        return deviceList;
    }
}
