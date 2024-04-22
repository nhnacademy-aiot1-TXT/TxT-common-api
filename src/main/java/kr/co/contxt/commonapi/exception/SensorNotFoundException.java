package kr.co.contxt.commonapi.exception;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException() {
    }

    public SensorNotFoundException(String info) {
        super(info);
    }
}
