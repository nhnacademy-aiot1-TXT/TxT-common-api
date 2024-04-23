package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.DeviceSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * DeviceSensor 접근을 위한 JpaRepository interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface DeviceSensorRepository extends JpaRepository<DeviceSensor, Long> {
    /**
     * device id로 장비별 센서 리스트 조회
     *
     * @param deviceId the device id
     * @return deviceSensor list
     */
    List<DeviceSensor> findByDevice_Id(Long deviceId);

    /**
     * device name으로 장비별 센서 리스트 조회
     *
     * @param deviceName the device name
     * @return deviceSensor list
     */
    List<DeviceSensor> findByDevice_Name(String deviceName);

    /**
     * device id, sensor id로 장비별 센서 단일 조회
     *
     * @param deviceId the device id
     * @param sensorId the sensor id
     * @return deviceSensor
     */
    Optional<DeviceSensor> findByDevice_IdAndSensor_SensorId(Long deviceId, Long sensorId);

    /**
     * device name, sensor name으로 장비별 센서 단일 조회
     *
     * @param deviceName the device name
     * @param sensorName the sensor name
     * @return deviceSensor
     */
    Optional<DeviceSensor> findByDevice_NameAndSensor_SensorName(String deviceName, String sensorName);
}
