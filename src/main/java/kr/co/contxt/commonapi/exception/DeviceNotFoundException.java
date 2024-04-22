package kr.co.contxt.commonapi.exception;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
