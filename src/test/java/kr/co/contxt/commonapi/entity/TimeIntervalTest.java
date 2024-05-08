package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.TimeIntervalResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeIntervalTest {
    @Test
    void timeIntervalEntityTest() {
        TimeInterval timeInterval = TimeInterval.builder()
                .timeIntervalId(1L)
                .sensor(new Sensor(1L, "test"))
                .begin(LocalTime.now())
                .end(LocalTime.now())
                .build();

        TimeIntervalResponse timeIntervalResponse = timeInterval.toDto();

        assertAll(
                () -> assertNotNull(timeIntervalResponse),
                () -> assertEquals(timeInterval.getSensor().getSensorId(), timeIntervalResponse.getSensorId()),
                () -> assertEquals(timeInterval.getSensor().getSensorName(), timeIntervalResponse.getSensorName()),
                () -> assertEquals(timeInterval.getBegin(), timeIntervalResponse.getBegin()),
                () -> assertEquals(timeInterval.getEnd(), timeIntervalResponse.getEnd())
        );
    }
}