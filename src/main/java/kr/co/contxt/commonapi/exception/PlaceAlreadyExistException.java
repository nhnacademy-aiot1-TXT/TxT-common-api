package kr.co.contxt.commonapi.exception;

/**
 * Place 저장시 이미 존재할때 발생하는 Exception class
 *
 * @author jongsikk
 * @version 1.0.0
 */
public class PlaceAlreadyExistException extends RuntimeException {
    public PlaceAlreadyExistException() {
    }

    public PlaceAlreadyExistException(String message) {
        super(message);
    }
}
