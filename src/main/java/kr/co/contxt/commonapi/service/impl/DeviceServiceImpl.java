package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    private static final String DEVICE_NOT_FOUND_MESSAGE = "Device를 찾을 수 없습니다.";

    /**
     * Device 리스트 조회 메서드
     *
     * @return device list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getDeviceList",
            key = "'all'",
            unless = "#result == null"
    )
    public List<DeviceResponse> getDeviceList() {
        return deviceRepository.findAll()
                .stream()
                .map(Device::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Device 단일 조회 메서드
     *
     * @param deviceId the device id
     * @return device
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getDeviceById",
            key = "#deviceId",
            unless = "#result == null"
    )
    public DeviceResponse getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(DEVICE_NOT_FOUND_MESSAGE))
                .toDto();
    }

    /**
     * Device 단일 조회 메서드
     *
     * @param deviceName the device name
     * @return device
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getDeviceByName",
            key = "#deviceName",
            unless = "#result == null"
    )
    public DeviceResponse getDeviceByName(String deviceName) {
        return deviceRepository.findByDeviceName(deviceName)
                .orElseThrow(() -> new DeviceNotFoundException(DEVICE_NOT_FOUND_MESSAGE))
                .toDto();
    }

    /**
     * Device 추가 메서드
     *
     * @param deviceRequest the device name and device cycle
     * @return device response
     */
    @Override
    @Transactional
    @CacheEvict(
            value = "getDeviceList",
            key = "'all'"
    )
    public DeviceResponse addDevice(DeviceRequest deviceRequest) {
        return deviceRepository.save(deviceRequest.toEntity()).toDto();
    }

    /**
     * Device 수정 메서드
     *
     * @param deviceId      the device id
     * @param deviceRequest the device name and device cycle
     * @return device response
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getDeviceList",
                            key = "'all'"
                    )
            },
            put = {
                    @CachePut(
                            value = "getDeviceById",
                            key = "#deviceId"
                    ),
                    @CachePut(
                            value = "getDeviceByName",
                            key = "#deviceRequest.getDeviceName()"
                    )
            }
    )
    public DeviceResponse updateDevice(Long deviceId, DeviceRequest deviceRequest) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(DEVICE_NOT_FOUND_MESSAGE));

        device.setDeviceName(deviceRequest.getDeviceName());
        device.setCycle(deviceRequest.getCycle());

        return deviceRepository.save(device).toDto();
    }
}
