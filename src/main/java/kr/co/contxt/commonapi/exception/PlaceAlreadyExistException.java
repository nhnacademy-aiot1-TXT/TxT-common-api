package kr.co.contxt.commonapi.exception;

public class PlaceAlreadyExistException extends RuntimeException {
    public PlaceAlreadyExistException() {
    }

    public PlaceAlreadyExistException(String message) {
        super(message);
    }
}
