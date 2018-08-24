package helper;

public class IncorrectPageLoadedException extends RuntimeException {
    public IncorrectPageLoadedException() {
    }

    public IncorrectPageLoadedException(String message) {
        super(message);
    }
}
