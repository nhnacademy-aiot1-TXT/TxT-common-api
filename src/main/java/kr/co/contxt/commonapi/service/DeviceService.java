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
     * Device 단일 조회 메서드
     *
     * @param deviceId the device id
     * @return the device
     */
    DeviceResponse getDevice(Long deviceId);

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
