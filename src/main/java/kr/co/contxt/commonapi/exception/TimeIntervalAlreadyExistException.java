package kr.co.contxt.commonapi.exception;

public class TimeIntervalAlreadyExistException extends RuntimeException {
    public TimeIntervalAlreadyExistException() {
    }

    public TimeIntervalAlreadyExistException(String message) {
        super(message);
    }
}
