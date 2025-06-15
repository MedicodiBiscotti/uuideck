package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.errorhandling.ShortException;

public class InvalidDataException extends RuntimeException implements ShortException {
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
