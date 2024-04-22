package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    boolean existsBySensorName(String sensorName);
}
