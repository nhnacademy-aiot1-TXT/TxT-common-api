package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.DeviceRequest;
import kr.co.contxt.commonapi.dto.DeviceResponse;
import kr.co.contxt.commonapi.entity.Device;
import kr.co.contxt.commonapi.exception.DeviceNotFoundException;
import kr.co.contxt.commonapi.repository.DeviceRepository;
import kr.co.contxt.commonapi.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    @Override
    public List<DeviceResponse> getDeviceList() {
        return deviceRepository.findAll()
                .stream()
                .map(Device::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeviceResponse getDevice(Long deviceId) {
        Optional<Device> response = deviceRepository.findById(deviceId);
        return response.orElseThrow(DeviceNotFoundException::new).toDto();
    }

    @Override
    public void updateDevice(Long deviceId, DeviceRequest deviceRequest) {
        Optional<Device> response = deviceRepository.findById(deviceId);

        if (response.isEmpty()) {
            throw new DeviceNotFoundException();
        }

        Device device = response.get();
        device.setName(deviceRequest.getDeviceName());
        device.setCycle(deviceRequest.getCycle());

        deviceRepository.save(device);
    }

    @Override
    public void addDevice(DeviceRequest deviceRequest) {
        deviceRepository.save(deviceRequest.toEntity());
    }
}
