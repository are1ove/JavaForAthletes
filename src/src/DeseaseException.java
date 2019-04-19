package src;


public class DeseaseException extends RuntimeException {
    public DeseaseException() {
    }

    public DeseaseException(String message) {
        super(message);
    }

    public DeseaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeseaseException(Throwable cause) {
        super(cause);
    }

    public DeseaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
