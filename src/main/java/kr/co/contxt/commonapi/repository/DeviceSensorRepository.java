package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.DeviceSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceSensorRepository extends JpaRepository<DeviceSensor, Long> {
    List<DeviceSensor> findByDevice_Id(Long deviceId);

    List<DeviceSensor> findByDevice_Name(String deviceName);

    Optional<DeviceSensor> findByDevice_IdAndSensor_SensorId(Long deviceId, Long sensorId);

    Optional<DeviceSensor> findByDevice_NameAndSensor_SensorName(String deviceName, String sensorName);
}
