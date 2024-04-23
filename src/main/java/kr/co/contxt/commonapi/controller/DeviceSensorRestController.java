package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/device-sensor")
@Tag(name = "Device Sensor Rest Controller", description = "장비별 센서 정보 API")
public class DeviceSensorRestController {
    private final DeviceSensorService deviceSensorService;

    @GetMapping("/{deviceId}")
    @Operation(summary = "장비별 센서 정보 리스트 조회")
    public ResponseEntity<List<DeviceSensorResponse>> getSensorListByDevice(@PathVariable Long deviceId) {
        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceId);

        return ResponseEntity.ok(deviceSensors);
    }

    @GetMapping("/{deviceId}/{sensorId}")
    @Operation(summary = "장비별 센서 정보 단일 조회")
    public ResponseEntity<DeviceSensorResponse> getSensorByDeviceAndSensor(@PathVariable Long deviceId, @PathVariable Long sensorId) {
        DeviceSensorResponse deviceSensor = deviceSensorService.getSensorByDeviceAndSensor(deviceId, sensorId);

        return ResponseEntity.ok(deviceSensor);
    }
}
