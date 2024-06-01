package kr.co.contxt.commonapi.exception;

/**
 * TimeInterval 저장시 이미 존재할때 발생하는 Exception class
 *
 * @author jongsikk
 * @version 1.0.0
 */
public class TimeIntervalAlreadyExistException extends RuntimeException {
    /**
     * 기본 생성자 메서드
     */
    public TimeIntervalAlreadyExistException() {
    }

    /**
     * message를 가지는 생성자 메서드
     *
     * @param message
     */
    public TimeIntervalAlreadyExistException(String message) {
        super(message);
    }
}
