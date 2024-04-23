package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Device service 구현 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    /**
     * 모든 Device 조회 메서드
     *
     * @return device list
     */
    @Override
    @Transactional(readOnly = true)
    public List<DeviceResponse> getDeviceList() {
        return deviceRepository.findAll()
                .stream()
                .map(Device::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 단일 Device 조회 메서드
     *
     * @param deviceId
     * @return device
     */
    @Override
    @Transactional(readOnly = true)
    public DeviceResponse getDevice(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device를 찾을 수 없습니다."))
                .toDto();
    }

    /**
     * Device 추가 메서드
     *
     * @param deviceRequest
     * @return device response
     */
    @Override
    @Transactional
    public DeviceResponse addDevice(DeviceRequest deviceRequest) {
        return deviceRepository.save(deviceRequest.toEntity()).toDto();
    }

    /**
     * Device 수정 메서드
     *
     * @param deviceId
     * @param deviceRequest
     * @return device response
     */
    @Override
    @Transactional
    public DeviceResponse updateDevice(Long deviceId, DeviceRequest deviceRequest) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device를 찾을 수 없습니다."));

        device.setName(deviceRequest.getDeviceName());
        device.setCycle(deviceRequest.getCycle());

        return deviceRepository.save(device).toDto();
    }
}
