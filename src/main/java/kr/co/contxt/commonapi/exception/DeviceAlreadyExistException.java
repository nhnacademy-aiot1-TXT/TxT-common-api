package kr.co.contxt.commonapi.exception;

/**
 * Device 저장시 이미 존재할때 발생하는 Exception class
 *
 * @author jongsikk
 * @version 1.0.0
 */
public class DeviceAlreadyExistException extends RuntimeException {
    public DeviceAlreadyExistException() {
    }

    public DeviceAlreadyExistException(String message) {
        super(message);
    }
}
