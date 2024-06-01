package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SensorRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private SensorRepository sensorRepository;

    @Test
    void existsBySensorName() {
        String sensorName = "test sensor2";
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();

        entityManager.persist(sensor);

        boolean result = sensorRepository.existsBySensorName(sensorName);

        assertAll(
                () -> assertTrue(result)
        );
    }

    @Test
    void findBySensorName() {
        String sensorName = "test sensor2";
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();

        entityManager.persist(sensor);

        Optional<Sensor> resultSensorOptional = sensorRepository.findBySensorName(sensorName);
        Sensor resultSensor = resultSensorOptional.get();

        assertAll(
                () -> assertNotNull(resultSensorOptional),
                () -> assertNotNull(resultSensor),
                () -> assertEquals(sensorName, resultSensor.getSensorName())
        );
    }
}