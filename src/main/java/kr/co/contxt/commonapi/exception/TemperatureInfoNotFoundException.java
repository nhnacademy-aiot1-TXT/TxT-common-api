package kr.co.contxt.commonapi.exception;

/**
 * 기상청 초단기 예보에서 온도 정보가 없을 때 발생하는 예외 class
 */
public class TemperatureInfoNotFoundException extends RuntimeException {
    /**
     * 날씨 예외 class 기본 생성자
     */
    public TemperatureInfoNotFoundException() {
    }

    /**
     * 날씨 예외 class에 메시지를 파라미터로 가지는 생성자
     *
     * @param message 예외 메시지
     */
    public TemperatureInfoNotFoundException(String message) {
        super(message);
    }
}
