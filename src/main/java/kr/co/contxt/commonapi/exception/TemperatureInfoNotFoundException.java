package kr.co.contxt.commonapi.exception;

public class TemperatureInfoNotFoundException extends RuntimeException {
    public TemperatureInfoNotFoundException() {
    }

    public TemperatureInfoNotFoundException(String info) {
        super(info);
    }
}
