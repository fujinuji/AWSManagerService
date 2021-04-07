package ro.fujinuji.awsmanager.model.exception;

public class AWSManagerException extends Exception {
    public AWSManagerException() {
    }

    public AWSManagerException(String message) {
        super(message);
    }

    public AWSManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public AWSManagerException(Throwable cause) {
        super(cause);
    }

    public AWSManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
