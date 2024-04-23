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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/device")
@Tag(name = "Device Rest Controller", description = "장비 정보 API")
public class DeviceRestController {
    private final DeviceService deviceService;

    @GetMapping
    @Operation(summary = "장비 정보 리스트 조회")
    public ResponseEntity<List<DeviceResponse>> getDeviceList() {
        return ResponseEntity.ok(deviceService.getDeviceList());
    }

    @GetMapping("/{deviceId}")
    @Operation(summary = "장비 정보 단일 조회")
    public ResponseEntity<DeviceResponse> getDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(deviceService.getDevice(deviceId));
    }

    @PostMapping
    @Operation(summary = "장비 정보 추가")
    public ResponseEntity<DeviceResponse> addDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.addDevice(deviceRequest));
    }

    @PutMapping("/{deviceId}")
    @Operation(summary = "장비 정보 수정")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable Long deviceId, @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.updateDevice(deviceId, deviceRequest));
    }
}
