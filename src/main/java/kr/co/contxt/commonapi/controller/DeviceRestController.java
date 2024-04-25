package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Device Rest Controller 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/device")
@Tag(name = "Device Rest Controller", description = "장비 정보 API")
public class DeviceRestController {
    private final DeviceService deviceService;

    /**
     * 장비 정보 리스트 조회 api
     *
     * @return the device list
     */
    @GetMapping("/devices")
    @Operation(summary = "장비 정보 리스트 조회")
    public ResponseEntity<List<DeviceResponse>> getDeviceList() {
        return ResponseEntity.ok(deviceService.getDeviceList());
    }

    /**
     * 장비 정보 ID로 단일 조회 api
     *
     * @param deviceId the device id
     * @return the device
     */
    @GetMapping("/{deviceId}")
    @Operation(summary = "장비 정보 단일 조회")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable Long deviceId) {
        return ResponseEntity.ok(deviceService.getDeviceById(deviceId));
    }

    /**
     * 장비 정보 이름으로 단일 조회 api
     *
     * @param name the device id
     * @return the device
     */
    @GetMapping
    @Operation(summary = "장비 정보 단일 조회")
    public ResponseEntity<DeviceResponse> getDeviceByName(@RequestParam String name) {
        return ResponseEntity.ok(deviceService.getDeviceByName(name));
    }

    /**
     * 장비 정보 추가 api
     *
     * @param deviceRequest the device request
     * @return the response entity
     */
    @PostMapping
    @Operation(summary = "장비 정보 추가")
    public ResponseEntity<DeviceResponse> addDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.addDevice(deviceRequest));
    }

    /**
     * 장비 정보 수정 api
     *
     * @param deviceId      the device id
     * @param deviceRequest the device request
     * @return the response entity
     */
    @PutMapping("/{deviceId}")
    @Operation(summary = "장비 정보 수정")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable Long deviceId, @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.updateDevice(deviceId, deviceRequest));
    }
}
