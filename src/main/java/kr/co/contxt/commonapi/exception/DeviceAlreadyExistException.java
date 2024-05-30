package kr.co.contxt.commonapi.exception;

public class DeviceAlreadyExistException extends RuntimeException {
    public DeviceAlreadyExistException() {
    }

    public DeviceAlreadyExistException(String message) {
        super(message);
    }
}
