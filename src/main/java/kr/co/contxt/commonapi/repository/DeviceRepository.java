package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Device Table 접근을 위한 JpaRepository interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
    /**
     * device name으로 Device 조회
     *
     * @param deviceName device name
     * @return device
     */
    Optional<Device> findByDeviceName(String deviceName);
}
