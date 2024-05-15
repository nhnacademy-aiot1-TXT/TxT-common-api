package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;

import java.util.List;

/**
 * Device Service interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
public interface DeviceService {
    /**
     * Gets device list.
     *
     * @return the device list
     */
    List<DeviceResponse> getDeviceList();

    /**
     * Gets device list by place.
     *
     * @return the device list
     */
    List<DeviceResponse> getDeviceListByPlace(Long placeId);

    /**
     * Device Id로 단일 조회 메서드
     *
     * @param deviceId the device id
     * @return the device
     */
    DeviceResponse getDeviceById(Long deviceId);

    /**
     * place 이름, device 이름으로 단일 조회 메서드
     *
     * @param placeName  the place name
     * @param deviceName the device name
     * @return the device
     */
    DeviceResponse getDeviceByPlaceAndName(String placeName, String deviceName);

    /**
     * Device 추가 메서드
     *
     * @param deviceRequest the device request
     * @return the device response
     */
    DeviceResponse addDevice(DeviceRequest deviceRequest);

    /**
     * Device 추가 메서드
     *
     * @param deviceId      the device id
     * @param deviceRequest the device request
     * @return the device response
     */
    DeviceResponse updateDevice(Long deviceId, DeviceRequest deviceRequest);

}
