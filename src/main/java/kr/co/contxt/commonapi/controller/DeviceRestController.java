package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.service.DeviceService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> addDevice(@RequestBody DeviceRequest deviceRequest) {
        deviceService.addDevice(deviceRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<Void> updateDevice(@PathVariable Long deviceId, @RequestBody DeviceRequest deviceRequest) {
        deviceService.updateDevice(deviceId, deviceRequest);
        return ResponseEntity.ok().build();
    }
}
