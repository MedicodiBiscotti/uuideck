package dk.kavv.uuideck.errorhandling;

public class MissingConfiguratonException extends RuntimeException implements ShortException {
    public MissingConfiguratonException(String message) {
        super(message);
    }
}
