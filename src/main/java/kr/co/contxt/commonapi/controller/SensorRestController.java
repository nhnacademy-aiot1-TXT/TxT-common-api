package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.SensorRequest;
import kr.co.contxt.commonapi.dto.SensorResponse;
import kr.co.contxt.commonapi.entity.Sensor;
import kr.co.contxt.commonapi.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 센서 정보 조회 api class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/sensor")
@Tag(name = "Sensor Rest Controller", description = "센서 정보 API")
public class SensorRestController {
    private final SensorService sensorService;

    /**
     * 센서 정보 리스트 조회 메서드
     *
     * @return 센서 정보 리스트
     */
    @GetMapping
    @Operation(summary = "센서 정보 리스트 조회")
    public ResponseEntity<List<SensorResponse>> getSensorList() {
        List<SensorResponse> sensors = sensorService.getAllSensors();

        return ResponseEntity.ok(sensors);
    }

    /**
     * 센서 정보 단일 조회 메서드
     *
     * @param sensorId 센서 아이디
     * @return 센서 정보
     */
    @GetMapping("/{sensorId}")
    @Operation(summary = "센서 정보 단일 조회")
    public ResponseEntity<SensorResponse> getSensor(@PathVariable Long sensorId) {
        SensorResponse sensor = sensorService.getSensor(sensorId);

        return ResponseEntity.ok(sensor);
    }

    /**
     * 센서 정보 추가 메서드
     *
     * @param sensorRequest 센서 추가 요청 dto
     * @return 추가된 센서 정보
     */
    @PostMapping
    @Operation(summary = "센서 정보 추가")
    public ResponseEntity<SensorResponse> addSensor(@RequestBody SensorRequest sensorRequest) {
        Sensor responseSensor = sensorService.saveSensor(sensorRequest.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSensor.toDto());
    }

    /**
     * 센서 정보 수정 메서드
     *
     * @param sensorId      센서 아이디
     * @param sensorRequest 센서 수정 요청 dto
     * @return 수정된 센서 정보
     */
    @PutMapping("/{sensorId}")
    @Operation(summary = "센서 정보 수정")
    public ResponseEntity<SensorResponse> updateSensor(@PathVariable Long sensorId, @RequestBody SensorRequest sensorRequest) {
        Sensor responseSensor = sensorService.updateSensor(sensorId, sensorRequest);

        return ResponseEntity.ok(responseSensor.toDto());
    }
}
