package kr.co.contxt.commonapi.exception;

public class SkyInfoNotFoundException extends RuntimeException {
    public SkyInfoNotFoundException() {
    }

    public SkyInfoNotFoundException(String info) {
        super(info);
    }
}
