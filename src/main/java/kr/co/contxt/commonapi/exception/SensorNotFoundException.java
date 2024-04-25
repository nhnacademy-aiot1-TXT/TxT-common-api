package kr.co.contxt.commonapi.exception;

/**
 * Sensor 정보가 없을 때 발생하는 Exception class
 *
 * @author parksangwon
 * @version 1.0.0
 */
public class SensorNotFoundException extends RuntimeException {
    /**
     * 기본 생성자
     */
    public SensorNotFoundException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message the message
     */
    public SensorNotFoundException(String message) {
        super(message);
    }
}
