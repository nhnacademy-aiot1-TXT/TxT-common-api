package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.DeviceAndSensorNameDto;
import kr.co.contxt.commonapi.dto.DeviceNameDto;
import kr.co.contxt.commonapi.dto.DeviceSensorRequest;
import kr.co.contxt.commonapi.dto.DeviceSensorResponse;
import kr.co.contxt.commonapi.service.DeviceSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * DeviceSensor Rest Controller 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/device-sensor")
@Tag(name = "Device Sensor Rest Controller", description = "장비별 센서 정보 API")
public class DeviceSensorRestController {
    private final DeviceSensorService deviceSensorService;

    /**
     * 장비 정보 ID로 센서 정보 리스트 조회 api
     *
     * @param deviceId the device id
     * @return the deviceSensor list
     */
    @GetMapping("/{deviceId}")
    @Operation(summary = "장비별 센서 정보 리스트 조회")
    public ResponseEntity<List<DeviceSensorResponse>> getSensorListByDevice(@PathVariable Long deviceId) {
        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceId);

        return ResponseEntity.ok(deviceSensors);
    }

    /**
     * 장비 정보 이름으로 센서 정보 리스트 조회 api
     *
     * @param deviceNameDto the device name dto
     * @return the deviceSensor list
     */
    @GetMapping("/sensors")
    @Operation(summary = "장비별 센서 정보 리스트 조회")
    public ResponseEntity<List<DeviceSensorResponse>> getSensorListByDeviceName(@ModelAttribute @Valid DeviceNameDto deviceNameDto) {
        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceNameDto);

        return ResponseEntity.ok(deviceSensors);
    }

    /**
     * 장비 정보 ID, 센서 정보 ID로 단일 조회 api
     *
     * @param deviceId the device id
     * @param sensorId the sensor id
     * @return the deviceSensor
     */
    @GetMapping("/{deviceId}/{sensorId}")
    @Operation(summary = "장비별 센서 정보 단일 조회")
    public ResponseEntity<DeviceSensorResponse> getSensorByDeviceAndSensor(@PathVariable Long deviceId, @PathVariable Long sensorId) {
        DeviceSensorResponse deviceSensor = deviceSensorService.getSensorByDeviceAndSensor(deviceId, sensorId);

        return ResponseEntity.ok(deviceSensor);
    }

    /**
     * 장비 정보 이름, 센서 정보 이름으로 단일 조회 api
     *
     * @param deviceAndSensorNameDto the device and sensor name dto
     * @return the deviceSensor
     */
    @GetMapping("/sensor")
    @Operation(summary = "장비별 센서 정보 단일 조회")
    public ResponseEntity<DeviceSensorResponse> getSensorByDeviceNameAndSensorName(@ModelAttribute @Valid DeviceAndSensorNameDto deviceAndSensorNameDto) {
        DeviceSensorResponse deviceSensor = deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorNameDto);

        return ResponseEntity.ok(deviceSensor);
    }

    /**
     * 장비 정보 이름, 센서 정보 이름으로 업데이트 api
     *
     * @param deviceId            the device id
     * @param sensorId            the sensor id
     * @param deviceSensorRequest 장비별 센서 on/off dto
     * @return 업데이트된 장비별 센서 dto
     */
    @PutMapping("/{deviceId}/{sensorId}")
    public ResponseEntity<DeviceSensorResponse> updateSensorByDeviceAndSensor(@PathVariable Long deviceId, @PathVariable Long sensorId, @RequestBody @Valid DeviceSensorRequest deviceSensorRequest) {
        DeviceSensorResponse deviceSensor = deviceSensorService.updateSensorByDeviceAndSensor(deviceId, sensorId, deviceSensorRequest);

        return ResponseEntity.ok(deviceSensor);
    }
}
