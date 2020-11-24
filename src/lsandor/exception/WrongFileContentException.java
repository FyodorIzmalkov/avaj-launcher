package lsandor.exception;

public class WrongFileContentException extends RuntimeException {

    public WrongFileContentException(String msg) {
        super(msg);
    }
}
