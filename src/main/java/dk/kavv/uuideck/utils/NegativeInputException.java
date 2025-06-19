package dk.kavv.uuideck.utils;

public class NegativeInputException extends RuntimeException {
    public NegativeInputException(int val) {
        super(String.format("Value %d cannot be negative", val));
    }
}
