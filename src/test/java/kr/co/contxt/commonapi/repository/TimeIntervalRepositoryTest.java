package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.entity.TimeInterval;
import kr.co.contxt.commonapi.exception.TimeIntervalNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TimeIntervalRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TimeIntervalRepository timeIntervalRepository;

    @Test
    void findBySensor_SensorId() {
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();
        TimeInterval timeInterval = TimeInterval.builder()
                .sensor(sensor)
                .begin(begin)
                .end(end)
                .build();

        entityManager.persist(sensor);
        entityManager.persist(timeInterval);

        Long timeIntervalId = timeInterval.getTimeIntervalId();
        Long sensorId = sensor.getSensorId();

        Optional<TimeInterval> timeIntervalOptional = timeIntervalRepository.findBySensor_SensorId(sensorId);
        TimeInterval timeIntervalResult = timeIntervalOptional.orElseThrow(TimeIntervalNotFoundException::new);

        assertAll(
                () -> assertNotNull(timeIntervalResult),
                () -> assertEquals(timeIntervalId, timeIntervalResult.getTimeIntervalId()),
                () -> assertEquals(sensor, timeInterval.getSensor()),
                () -> assertEquals(begin, timeIntervalResult.getBegin()),
                () -> assertEquals(end, timeIntervalResult.getEnd())
        );
    }

    @Test
    void findBySensor_SensorName() {
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();
        TimeInterval timeInterval = TimeInterval.builder()
                .sensor(sensor)
                .begin(begin)
                .end(end)
                .build();

        entityManager.persist(sensor);
        entityManager.persist(timeInterval);

        Long timeIntervalId = timeInterval.getTimeIntervalId();

        Optional<TimeInterval> timeIntervalOptional = timeIntervalRepository.findBySensor_SensorName(sensorName);
        TimeInterval timeIntervalResult = timeIntervalOptional.orElseThrow(TimeIntervalNotFoundException::new);

        assertAll(
                () -> assertNotNull(timeIntervalResult),
                () -> assertEquals(timeIntervalId, timeIntervalResult.getTimeIntervalId()),
                () -> assertEquals(sensor, timeInterval.getSensor()),
                () -> assertEquals(begin, timeIntervalResult.getBegin()),
                () -> assertEquals(end, timeIntervalResult.getEnd())
        );
    }

    @Test
    void existsBySensor_SensorId() {
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String sensorName = "test sensor";
        Sensor sensor = Sensor.builder()
                .sensorName(sensorName)
                .build();
        TimeInterval timeInterval = TimeInterval.builder()
                .sensor(sensor)
                .begin(begin)
                .end(end)
                .build();

        entityManager.persist(sensor);
        entityManager.persist(timeInterval);

        Long sensorId = sensor.getSensorId();

        boolean existTimeInterval = timeIntervalRepository.existsBySensor_SensorId(sensorId);

        assertAll(
                () -> assertTrue(existTimeInterval)
        );
    }
}