package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;

import java.util.List;

public interface DeviceService {
    List<DeviceResponse> getDeviceList();

    DeviceResponse getDevice(Long deviceId);

    DeviceResponse updateDevice(Long deviceId, DeviceRequest deviceRequest);

    DeviceResponse addDevice(DeviceRequest deviceRequest);
}
