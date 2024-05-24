package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.DeviceAndPlaceNameDto;
import kr.co.contxt.commonapi.dto.DeviceAndSensorAndPlaceNameDto;
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
     * @param deviceAndPlaceNameDto the device name and place name dto
     * @return the deviceSensor list
     */
    @GetMapping("/sensors")
    @Operation(summary = "장비별 센서 정보 리스트 조회")
    public ResponseEntity<List<DeviceSensorResponse>> getSensorListByDeviceName(@ModelAttribute @Valid DeviceAndPlaceNameDto deviceAndPlaceNameDto) {
        List<DeviceSensorResponse> deviceSensors = deviceSensorService.getSensorListByDevice(deviceAndPlaceNameDto);

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
     * @param deviceAndSensorAndPlaceNameDto the device and sensor name and place name dto
     * @return the deviceSensor
     */
    @GetMapping("/sensor")
    @Operation(summary = "장비별 센서 정보 단일 조회")
    public ResponseEntity<DeviceSensorResponse> getSensorByDeviceNameAndSensorName(@ModelAttribute @Valid DeviceAndSensorAndPlaceNameDto deviceAndSensorAndPlaceNameDto) {
        DeviceSensorResponse deviceSensor = deviceSensorService.getSensorByDeviceAndSensor(deviceAndSensorAndPlaceNameDto);

        return ResponseEntity.ok(deviceSensor);
    }

    /**
     * 장비 정보 이름, 센서 정보 이름으로 업데이트 api
     *
     * @param deviceSensorRequest 장비별 센서 on/off dto
     * @return 업데이트된 장비별 센서 dto
     */
    @PutMapping
    public ResponseEntity<DeviceSensorResponse> updateSensorByDeviceAndSensor(@RequestBody @Valid DeviceSensorRequest deviceSensorRequest) {
        DeviceSensorResponse deviceSensor = deviceSensorService.updateSensorByDeviceAndSensor(deviceSensorRequest);

        return ResponseEntity.ok(deviceSensor);
    }
}
