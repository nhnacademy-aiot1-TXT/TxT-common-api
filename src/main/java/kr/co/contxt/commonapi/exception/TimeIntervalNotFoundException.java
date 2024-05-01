package kr.co.contxt.commonapi.exception;

/**
 * TimeInterval 정보가 없을 때 발생하는 Exception 클래스
 */
public class TimeIntervalNotFoundException extends RuntimeException {
    /**
     * 기본생성자
     */
    public TimeIntervalNotFoundException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message the message
     */
    public TimeIntervalNotFoundException(String message) {
        super(message);
    }
}
