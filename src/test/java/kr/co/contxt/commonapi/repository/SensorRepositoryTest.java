package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Sensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertTrue(result);
    }
}