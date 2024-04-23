package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Device Table 접근을 위한 JpaRepository interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
