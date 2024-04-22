package kr.co.contxt.commonapi.exception;

public class DeviceSensorNotFoundException extends RuntimeException {
    public DeviceSensorNotFoundException() {
    }

    public DeviceSensorNotFoundException(String info) {
        super(info);
    }
}
