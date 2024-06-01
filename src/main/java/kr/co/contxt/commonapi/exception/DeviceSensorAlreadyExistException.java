package kr.co.contxt.commonapi.exception;

/**
 * DeviceSensor 저장시 이미 존재할때 발생하는 Exception class
 *
 * @author jongsikk
 * @version 1.0.0
 */
public class DeviceSensorAlreadyExistException extends RuntimeException {
    public DeviceSensorAlreadyExistException() {
    }

    public DeviceSensorAlreadyExistException(String message) {
        super(message);
    }
}
