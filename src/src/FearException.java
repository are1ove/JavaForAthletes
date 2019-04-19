package src;


public class FearException extends Exception {
    public FearException() {
    }

    public FearException(String message) {
        super(message);
    }

    public FearException(String message, Throwable cause) {
        super(message, cause);
    }

    public FearException(Throwable cause) {
        super(cause);
    }

    public FearException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

