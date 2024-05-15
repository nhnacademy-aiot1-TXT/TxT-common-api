package kr.co.contxt.commonapi.exception;

/**
 * 장소가 없을 때 발생하는 Exception class
 *
 * @author parksangwon
 * @version 1.0.0
 */
public class PlaceNotFountException extends RuntimeException {
    /**
     * 기본 생성자
     */
    public PlaceNotFountException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message the message
     */
    public PlaceNotFountException(String message) {
        super(message);
    }
}
