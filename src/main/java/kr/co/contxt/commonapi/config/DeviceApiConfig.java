package kr.co.contxt.commonapi.config;

import kr.co.contxt.commonapi.entity.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Device 정보 초기 데이터 설정 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Configuration
public class DeviceApiConfig {
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
}
