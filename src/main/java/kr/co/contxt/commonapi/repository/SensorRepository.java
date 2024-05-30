package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Sensor Table 접근을 위한 JpaRepository interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    /**
     * 센서 이름이 존재하는지 확인하는 메서드
     *
     * @param sensorName 센서 이름
     * @return 존재 여부
     */
    boolean existsBySensorName(String sensorName);

    /**
     * 센서 이름에 해당하는 센서를 반환하는 메서드
     *
     * @param sensorName 센서 이름
     * @return 센서
     */
    Optional<Sensor> findBySensorName(String sensorName);
}
