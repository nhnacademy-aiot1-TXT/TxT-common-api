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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/sensor")
@Tag(name = "Sensor Rest Controller", description = "센서 정보 API")
public class SensorRestController {
    private final SensorService sensorService;

    @GetMapping
    @Operation(summary = "센서 정보 리스트 조회")
    public ResponseEntity<List<SensorResponse>> getSensorList() {
        List<SensorResponse> sensors = sensorService.getAllSensors();

        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/{sensorId}")
    @Operation(summary = "센서 정보 단일 조회")
    public ResponseEntity<SensorResponse> getSensor(@PathVariable Long sensorId) {
        SensorResponse sensor = sensorService.getSensor(sensorId);

        return ResponseEntity.ok(sensor);
    }

    @PostMapping
    @Operation(summary = "센서 정보 추가")
    public ResponseEntity<SensorResponse> addSensor(@RequestBody SensorRequest sensorRequest) {
        Sensor responseSensor = sensorService.saveSensor(sensorRequest.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSensor.toDto());
    }

    @PutMapping("/{sensorId}")
    @Operation(summary = "센서 정보 수정")
    public ResponseEntity<SensorResponse> updateSensor(@PathVariable Long sensorId, @RequestBody SensorRequest sensorRequest) {
        Sensor responseSensor = sensorService.updateSensor(sensorId, sensorRequest);

        return ResponseEntity.ok(responseSensor.toDto());
    }
}
