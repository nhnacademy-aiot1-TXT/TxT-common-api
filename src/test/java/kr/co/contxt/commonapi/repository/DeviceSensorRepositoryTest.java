package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.entity.DeviceSensor;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.exception.DeviceSensorNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeviceSensorRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DeviceSensorRepository deviceSensorRepository;

    @Test
    void findByDevice_Id() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        Float onValue = 25F;
        Float offValue = 22F;

        Device device = Device.builder()
                .name(deviceName)
                .cycle(LocalTime.of(0, 10))
                .build();
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(device)
                .sensor(sensor)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        entityManager.persist(device);
        entityManager.persist(sensor);
        entityManager.persist(deviceSensor);

        Long deviceId = device.getId();
        Long deviceSensorId = deviceSensor.getDeviceSensorId();

        List<DeviceSensor> deviceSensors = deviceSensorRepository.findByDevice_Id(deviceId);

        assertAll(
                () -> assertNotNull(deviceSensors),
                () -> assertFalse(deviceSensors.isEmpty()),
                () -> assertEquals(deviceSensorId, deviceSensors.get(0).getDeviceSensorId()),
                () -> assertEquals(device, deviceSensors.get(0).getDevice()),
                () -> assertEquals(sensor, deviceSensors.get(0).getSensor()),
                () -> assertEquals(onValue, deviceSensors.get(0).getOnValue()),
                () -> assertEquals(offValue, deviceSensors.get(0).getOffValue())
        );
    }

    @Test
    void findByDevice_IdAndSensor_SensorId() {
        String deviceName = "test device";
        String sensorName = "test sensor";
        Float onValue = 25F;
        Float offValue = 22F;

        Device device = Device.builder()
                .name(deviceName)
                .cycle(LocalTime.of(0, 10))
                .build();
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();
        DeviceSensor deviceSensor = DeviceSensor.builder()
                .device(device)
                .sensor(sensor)
                .onValue(onValue)
                .offValue(offValue)
                .build();

        entityManager.persist(device);
        entityManager.persist(sensor);
        entityManager.persist(deviceSensor);

        Long deviceId = device.getId();
        Long sensorId = sensor.getSensorId();
        Long deviceSensorId = deviceSensor.getDeviceSensorId();

        Optional<DeviceSensor> deviceSensorOptional = deviceSensorRepository.findByDevice_IdAndSensor_SensorId(deviceId, sensorId);
        DeviceSensor deviceSensorResult = deviceSensorOptional.orElseThrow(DeviceSensorNotFoundException::new);

        assertAll(
                () -> assertNotNull(deviceSensorResult),
                () -> assertEquals(deviceSensorId, deviceSensorResult.getDeviceSensorId()),
                () -> assertEquals(device, deviceSensorResult.getDevice()),
                () -> assertEquals(sensor, deviceSensorResult.getSensor()),
                () -> assertEquals(onValue, deviceSensorResult.getOnValue()),
                () -> assertEquals(offValue, deviceSensorResult.getOffValue())
        );
    }
}