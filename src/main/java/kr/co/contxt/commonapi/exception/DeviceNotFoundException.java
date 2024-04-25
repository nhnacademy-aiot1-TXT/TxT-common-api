package kr.co.contxt.commonapi.exception;

/**
 * Device 정보가 없을 때 발생하는 Exception 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
public class DeviceNotFoundException extends RuntimeException {
    /**
     * 기본생성자
     */
    public DeviceNotFoundException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message the message
     */
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
