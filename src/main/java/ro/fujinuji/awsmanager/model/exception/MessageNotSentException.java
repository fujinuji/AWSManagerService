package ro.fujinuji.awsmanager.model.exception;

public class MessageNotSentException extends AWSManagerException {
    public MessageNotSentException() {
    }

    public MessageNotSentException(String message) {
        super(message);
    }

    public MessageNotSentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageNotSentException(Throwable cause) {
        super(cause);
    }

    public MessageNotSentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
