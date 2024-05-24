package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.DeviceSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * DeviceSensor Table 접근을 위한 JpaRepository interface
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
    List<DeviceSensor> findByDevice_DeviceId(Long deviceId);

    /**
     * device name으로 장비별 센서 리스트 조회
     *
     * @param deviceName the device name
     * @param placeName  the place name
     * @return deviceSensor list
     */
    List<DeviceSensor> findByDevice_DeviceNameAndDevice_Place_PlaceCode(String deviceName, String placeName);

    /**
     * device id, sensor id로 장비별 센서 단일 조회
     *
     * @param deviceId the device id
     * @param sensorId the sensor id
     * @return deviceSensor
     */
    Optional<DeviceSensor> findByDevice_DeviceIdAndSensor_SensorId(Long deviceId, Long sensorId);

    /**
     * device name, sensor name으로 장비별 센서 단일 조회
     *
     * @param deviceName the device name
     * @param sensorName the sensor name
     * @param placeName  the place name
     * @return deviceSensor
     */
    Optional<DeviceSensor> findByDevice_DeviceNameAndSensor_SensorNameAndDevice_Place_PlaceCode(String deviceName, String sensorName, String placeName);

    /**
     * 장치 아이디와 센서 아이디로 장치별 센서가 존재하는지 체크
     *
     * @param deviceId the device id
     * @param sensorId the sensor id
     * @return boolean
     */
    boolean existsByDevice_DeviceIdAndSensor_SensorId(Long deviceId, Long sensorId);
}
