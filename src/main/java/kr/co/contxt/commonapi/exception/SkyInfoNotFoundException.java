package kr.co.contxt.commonapi.exception;

/**
 * 기상청 초단기 예보에서 날씨 정보가 없을 때 발생하는 Exception 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
public class SkyInfoNotFoundException extends RuntimeException {
    /**
     * 기본 생성자
     */
    public SkyInfoNotFoundException() {
    }

    /**
     * message를 가지는 생성자
     *
     * @param message the message
     */
    public SkyInfoNotFoundException(String message) {
        super(message);
    }
}
