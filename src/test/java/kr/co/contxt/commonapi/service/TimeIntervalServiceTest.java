package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.SensorNameDto;
import kr.co.contxt.commonapi.dto.TimeIntervalRequest;
import kr.co.contxt.commonapi.dto.TimeIntervalResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.entity.TimeInterval;
import kr.co.contxt.commonapi.exception.TimeIntervalNotFoundException;
import kr.co.contxt.commonapi.repository.TimeIntervalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(TimeIntervalService.class)
class TimeIntervalServiceTest {
    @Autowired
    private TimeIntervalService timeIntervalService;
    @MockBean
    private TimeIntervalRepository timeIntervalRepository;

    @Test
    void getTimeInterval() {
        Long timeIntervalId = 1L;
        Long sensorId = 1L;
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String sensorName = "test sensor";
        TimeInterval timeInterval = TimeInterval.builder()
                .timeIntervalId(timeIntervalId)
                .sensor(new Sensor(sensorId, sensorName))
                .begin(begin)
                .end(end)
                .build();

        given(timeIntervalRepository.findBySensor_SensorId(anyLong())).willReturn(Optional.of(timeInterval));

        TimeIntervalResponse timeIntervalResponse = timeIntervalService.getTimeInterval(sensorId);

        assertAll(
                () -> assertNotNull(timeIntervalResponse),
                () -> assertEquals(sensorName, timeIntervalResponse.getSensorName()),
                () -> assertEquals(begin, timeIntervalResponse.getBegin()),
                () -> assertEquals(end, timeIntervalResponse.getEnd())
        );
    }

    @Test
    void getTimeIntervalException() {
        given(timeIntervalRepository.findBySensor_SensorId(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(TimeIntervalNotFoundException.class, () -> timeIntervalService.getTimeInterval(1L));
    }

    @Test
    void getTimeIntervalBySensorName() {
        Long timeIntervalId = 1L;
        Long sensorId = 1L;
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String sensorName = "test sensor";
        SensorNameDto sensorNameDto = new SensorNameDto(sensorName);
        TimeInterval timeInterval = TimeInterval.builder()
                .timeIntervalId(timeIntervalId)
                .sensor(new Sensor(sensorId, sensorName))
                .begin(begin)
                .end(end)
                .build();

        given(timeIntervalRepository.findBySensor_SensorName(anyString())).willReturn(Optional.of(timeInterval));

        TimeIntervalResponse timeIntervalResponse = timeIntervalService.getTimeInterval(sensorNameDto);

        assertAll(
                () -> assertNotNull(timeIntervalResponse),
                () -> assertEquals(sensorName, timeIntervalResponse.getSensorName()),
                () -> assertEquals(begin, timeIntervalResponse.getBegin()),
                () -> assertEquals(end, timeIntervalResponse.getEnd())
        );
    }

    @Test
    void createTimeInterval() {
        Long sensorId = 1L;
        String sensorName = "test sensor";
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        TimeIntervalRequest timeIntervalRequest = new TimeIntervalRequest(sensorId, sensorName, begin, end);

        timeIntervalService.createTimeInterval(timeIntervalRequest);

        verify(timeIntervalRepository, times(1)).save(any());
    }

    @Test
    void updateTimeInterval() {
        Long timeIntervalId = 1L;
        Long sensorId = 1L;
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        String sensorName = "test sensor";
        TimeIntervalRequest timeIntervalRequest = new TimeIntervalRequest(sensorId, sensorName, begin, end);
        TimeInterval timeInterval = TimeInterval.builder()
                .timeIntervalId(timeIntervalId)
                .sensor(new Sensor(sensorId, sensorName))
                .begin(begin)
                .end(end)
                .build();

        given(timeIntervalRepository.findById(anyLong())).willReturn(Optional.of(timeInterval));
        given(timeIntervalRepository.save(any())).willReturn(timeInterval);

        TimeIntervalResponse timeIntervalResponse = timeIntervalService.updateTimeInterval(timeIntervalId, timeIntervalRequest);

        assertAll(
                () -> assertNotNull(timeIntervalResponse),
                () -> assertEquals(sensorName, timeIntervalResponse.getSensorName()),
                () -> assertEquals(begin, timeIntervalResponse.getBegin()),
                () -> assertEquals(end, timeIntervalResponse.getEnd())
        );
    }

    @Test
    void updateTimeIntervalException() {
        Long sensorId = 1L;
        String sensorName = "test sensor";
        LocalTime begin = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.of(7, 0, 0);
        TimeIntervalRequest timeIntervalRequest = new TimeIntervalRequest(sensorId, sensorName, begin, end);

        given(timeIntervalRepository.findById(anyLong())).willReturn(Optional.empty());


        assertThrows(TimeIntervalNotFoundException.class, () -> timeIntervalService.updateTimeInterval(1L, timeIntervalRequest));
    }
}