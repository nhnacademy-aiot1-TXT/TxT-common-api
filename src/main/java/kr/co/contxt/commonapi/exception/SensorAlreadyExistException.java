package kr.co.contxt.commonapi.exception;

public class SensorAlreadyExistException extends RuntimeException {
    public SensorAlreadyExistException() {
    }

    public SensorAlreadyExistException(String message) {
        super(message);
    }
}
