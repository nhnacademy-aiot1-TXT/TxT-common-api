package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.SensorNameDto;
import kr.co.contxt.commonapi.dto.TimeIntervalRequest;
import kr.co.contxt.commonapi.dto.TimeIntervalResponse;
import kr.co.contxt.commonapi.service.TimeIntervalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TimeInterval Rest Controller
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/time-interval")
@Tag(name = "Time Interval Rest Controller", description = "침입 탐지 시간 API")
public class TimeIntervalRestController {
    private final TimeIntervalService timeIntervalService;

    /**
     * 센서 ID로 탐지 시간 조회 api
     *
     * @param sensorId the sensor id
     * @return the time interval by sensor
     */
    @GetMapping("/{sensorId}")
    @Operation(summary = "센서 아이디로 탐지 시간 조회")
    public ResponseEntity<TimeIntervalResponse> getTimeIntervalBySensor(@PathVariable Long sensorId) {
        TimeIntervalResponse timeIntervalResponse = timeIntervalService.getTimeInterval(sensorId);

        return ResponseEntity.ok(timeIntervalResponse);
    }

    /**
     * 센서 이름으로 탐지 시간 조회 api
     *
     * @param sensorNameDto the sensor name dto
     * @return the time interval by sensor name
     */
    @GetMapping
    @Operation(summary = "센서 이름으로 탐지 시간 조회")
    public ResponseEntity<TimeIntervalResponse> getTimeIntervalBySensorName(@ModelAttribute SensorNameDto sensorNameDto) {
        TimeIntervalResponse timeIntervalResponse = timeIntervalService.getTimeInterval(sensorNameDto);

        return ResponseEntity.ok(timeIntervalResponse);
    }

    /**
     * 탐지 시간 추가 api
     *
     * @param timeIntervalRequest the time interval request
     * @return the response entity
     */
    @PostMapping
    @Operation(summary = "탐지 시간 추가")
    public ResponseEntity<Void> addTimeInterval(@RequestBody TimeIntervalRequest timeIntervalRequest) {
        timeIntervalService.createTimeInterval(timeIntervalRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    /**
     * 탐지 시간 수정 api
     *
     * @param timeIntervalId      the time interval id
     * @param timeIntervalRequest the time interval request
     * @return the response entity
     */
    @PutMapping("/{timeIntervalId}")
    @Operation(summary = "탐지 시간 수정")
    public ResponseEntity<TimeIntervalResponse> updateTimeInterval(@PathVariable Long timeIntervalId, @RequestBody TimeIntervalRequest timeIntervalRequest) {
        TimeIntervalResponse timeIntervalResponse = timeIntervalService.updateTimeInterval(timeIntervalId, timeIntervalRequest);

        return ResponseEntity.ok(timeIntervalResponse);
    }
}
