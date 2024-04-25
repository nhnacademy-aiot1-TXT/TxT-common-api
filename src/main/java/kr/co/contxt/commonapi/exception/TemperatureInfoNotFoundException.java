package kr.co.contxt.commonapi.exception;

/**
 * 기상청 초단기 예보에서 온도 정보가 없을 때 발생하는 Exception 클래스
 */
public class TemperatureInfoNotFoundException extends RuntimeException {
    /**
     * 기본 생성자
     */
    public TemperatureInfoNotFoundException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message the message
     */
    public TemperatureInfoNotFoundException(String message) {
        super(message);
    }
}
