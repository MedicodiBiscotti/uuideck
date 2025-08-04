package dk.kavv.uuideck.encoding;

public class InvalidDataException extends RuntimeException {
    private static final String STANDARD_MESSAGE = "Malformed input could not be parsed";

    public InvalidDataException() {
        super(STANDARD_MESSAGE);
    }

    public InvalidDataException(Throwable cause) {
        super(STANDARD_MESSAGE, cause);
    }

    public InvalidDataException(String message, Throwable t) {
        super(String.format("%s: %s", STANDARD_MESSAGE, message), t);
    }
}
