package t1.limitservice.exception;

public class LimitExhaustedException extends RuntimeException {
    public LimitExhaustedException(String message) {
        super(message);
    }
}
