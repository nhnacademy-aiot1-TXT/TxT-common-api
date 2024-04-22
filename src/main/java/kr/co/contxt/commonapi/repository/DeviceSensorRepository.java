package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.DeviceSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceSensorRepository extends JpaRepository<DeviceSensor, Long> {
    List<DeviceSensor> findByDevice_Id(Long deviceId);

    Optional<DeviceSensor> findByDevice_IdAndSensor_SensorId(Long deviceId, Long sensorId);
}
