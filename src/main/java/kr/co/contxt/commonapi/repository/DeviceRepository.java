package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Device Table 접근을 위한 JpaRepository interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
    /**
     * place id로 Device 조회
     *
     * @param placeId place id
     * @return device
     */
    List<Device> findByPlace_PlaceId(Long placeId);

    /**
     * device name으로 Device 조회
     *
     * @param placeCode  place code
     * @param deviceName device name
     * @return device
     */
    Optional<Device> findByPlace_PlaceCodeAndDeviceName(String placeCode, String deviceName);

    /**
     * device name, place id로 존재여부 조회
     *
     * @param deviceName device name
     * @param placeId    place id
     * @return boolean
     */
    boolean existsByDeviceNameAndPlace_PlaceId(String deviceName, Long placeId);
}
