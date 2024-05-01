package kr.co.contxt.commonapi.exception;

/**
 * DeviceSensor 정보가 없을 때 발생하는 Exception 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
public class DeviceSensorNotFoundException extends RuntimeException {
    /**
     * 기본 생성자
     */
    public DeviceSensorNotFoundException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message themessage
     */
    public DeviceSensorNotFoundException(String message) {
        super(message);
    }
}
