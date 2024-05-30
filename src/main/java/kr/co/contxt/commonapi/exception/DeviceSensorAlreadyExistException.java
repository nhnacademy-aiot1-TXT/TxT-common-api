package kr.co.contxt.commonapi.exception;

public class DeviceSensorAlreadyExistException extends RuntimeException {
    public DeviceSensorAlreadyExistException() {
    }

    public DeviceSensorAlreadyExistException(String message) {
        super(message);
    }
}
