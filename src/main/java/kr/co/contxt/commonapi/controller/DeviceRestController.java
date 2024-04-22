package kr.co.contxt.commonapi.controller;

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
public class DeviceRestController {
    private final DeviceService deviceService;

    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getDeviceList() {
        return ResponseEntity.ok(deviceService.getDeviceList());
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceResponse> getDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(deviceService.getDevice(deviceId));
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> addDevice(@RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.addDevice(deviceRequest));
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable Long deviceId, @RequestBody DeviceRequest deviceRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.updateDevice(deviceId, deviceRequest));
    }
}
