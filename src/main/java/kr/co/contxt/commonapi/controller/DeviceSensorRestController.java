package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sensors")
    public ResponseEntity<List<DeviceSensorResponse>> getSensorListByDeviceName(@ModelAttribute DeviceNameDto deviceNameDto) {
        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceNameDto);

        return ResponseEntity.ok(deviceSensors);
    }

    @GetMapping("/{deviceId}/{sensorId}")
    @Operation(summary = "장비별 센서 정보 단일 조회")
    public ResponseEntity<DeviceSensorResponse> getSensorByDeviceAndSensor(@PathVariable Long deviceId, @PathVariable Long sensorId) {
        DeviceSensorResponse deviceSensor = deviceSensorService.getSensorByDeviceAndSensor(deviceId, sensorId);

        return ResponseEntity.ok(deviceSensor);
    }

    @GetMapping("/sensor")
    public ResponseEntity<DeviceSensorResponse> getSensorByDeviceNameAndSensorName(@ModelAttribute DeviceAndSensorNameDto deviceAndSensorNameDto) {
        DeviceSensorResponse deviceSensor = deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto);

        return ResponseEntity.ok(deviceSensor);
    }
}
