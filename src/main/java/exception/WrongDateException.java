package exception;

/**
 *
 */
public class WrongDateException extends RuntimeException {
    public WrongDateException() {
    }

    public WrongDateException(String message) {
        super(message);
    }
}
