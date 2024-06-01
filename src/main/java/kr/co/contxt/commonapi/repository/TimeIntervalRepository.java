package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.TimeInterval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * TimeIntervalRepository 접근을 위한 JpaRepository interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface TimeIntervalRepository extends JpaRepository<TimeInterval, Long> {
    /**
     * 센서 아이디로 탐지 시간을 조회하는 메서드
     *
     * @param sensorId the sensor id
     * @return the optional
     */
    Optional<TimeInterval> findBySensor_SensorId(Long sensorId);

    /**
     * 센서 이름으로 탐지 시간을 조회하는 메서드
     *
     * @param sensorName the sensor name
     * @return the optional
     */
    Optional<TimeInterval> findBySensor_SensorName(String sensorName);

    /**
     * 센서 아이디로 탐지 시간을 체크하는 메서드
     *
     * @param sensorId the sensor id
     * @return boolean
     */
    boolean existsBySensor_SensorId(Long sensorId);
}
