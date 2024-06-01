package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.exception.DeviceAlreadyExistException;
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
    private static final String DEVICE_NOT_FOUND_MESSAGE = "장치를 찾을 수 없습니다.";
    private static final String DEVICE_ALREADY_EXIST_MESSAGE = "장치가 이미 존재합니다.";

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
     * 장소별 Device 리스트 조회 메서드
     *
     * @param placeId the placeId
     * @return device list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getDeviceListByPlace",
            key = "#placeId",
            unless = "#result == null"
    )
    public List<DeviceResponse> getDeviceListByPlace(Long placeId) {
        return deviceRepository.findByPlace_PlaceId(placeId)
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
     * @param placeName  the place name
     * @param deviceName the device name
     * @return device
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getDeviceByPlaceAndName",
            key = "#placeName.concat('-').concat(#deviceName)",
            unless = "#result == null"
    )
    public DeviceResponse getDeviceByPlaceAndName(String placeName, String deviceName) {
        return deviceRepository.findByPlace_PlaceCodeAndDeviceName(placeName, deviceName)
                .orElseThrow(() -> new DeviceNotFoundException(DEVICE_NOT_FOUND_MESSAGE))
                .toDto();
    }

    /**
     * Device 추가 메서드
     *
     * @param deviceRequest the place name and device name
     * @return device response
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getDeviceList",
                            key = "'all'"
                    ),
                    @CacheEvict(
                            value = "getDeviceListByPlace",
                            key = "#deviceRequest.placeId"
                    )
            }
    )
    public DeviceResponse addDevice(DeviceRequest deviceRequest) {
        if (deviceRepository.existsByDeviceNameAndPlace_PlaceId(deviceRequest.getDeviceName(), deviceRequest.getPlaceId())) {
            throw new DeviceAlreadyExistException(DEVICE_ALREADY_EXIST_MESSAGE);
        }
        return deviceRepository.save(deviceRequest.toEntity()).toDto();
    }

    /**
     * Device 수정 메서드
     *
     * @param deviceId      the device id
     * @param deviceRequest the device name
     * @return device response
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getDeviceList",
                            key = "'all'"
                    ),
                    @CacheEvict(
                            value = "getDeviceListByPlace",
                            key = "#deviceRequest.placeId"
                    )
            },
            put = {
                    @CachePut(
                            value = "getDeviceById",
                            key = "#deviceId"
                    )
            }
    )
    public DeviceResponse updateDevice(Long deviceId, DeviceRequest deviceRequest) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(DEVICE_NOT_FOUND_MESSAGE));

        device.setDeviceName(deviceRequest.getDeviceName());

        return deviceRepository.save(device).toDto();
    }
}
