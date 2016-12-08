package sample.boot.exception;

public class ErrorHandleException extends RuntimeException {

    public ErrorHandleException(final String message) {
        super(message);
    }
}
