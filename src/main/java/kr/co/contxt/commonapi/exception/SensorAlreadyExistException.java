package kr.co.contxt.commonapi.exception;

/**
 * Sensor 저장시 이미 존재할때 발생하는 Exception class
 *
 * @author jongsikk
 * @version 1.0.0
 */
public class SensorAlreadyExistException extends RuntimeException {
    /**
     * 기본 생성자 메서드
     */
    public SensorAlreadyExistException() {
    }

    /**
     * message를 가지는 생성자 메서드
     *
     * @param message
     */
    public SensorAlreadyExistException(String message) {
        super(message);
    }
}
