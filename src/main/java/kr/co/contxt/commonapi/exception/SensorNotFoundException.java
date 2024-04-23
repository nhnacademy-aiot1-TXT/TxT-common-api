package kr.co.contxt.commonapi.exception;

/**
 * 센서 정보 조회에서 값이 없을 때 발생하는 예외 class
 *
 * @author parksangwon
 * @version 1.0.0
 */
public class SensorNotFoundException extends RuntimeException {
    /**
     * 센서 예외 class 기본 생성자
     */
    public SensorNotFoundException() {
    }

    /**
     * 센서 예외 class에 메시지를 파라미터로 가지는 생성자
     *
     * @param message 예외 메시지
     */
    public SensorNotFoundException(String message) {
        super(message);
    }
}
