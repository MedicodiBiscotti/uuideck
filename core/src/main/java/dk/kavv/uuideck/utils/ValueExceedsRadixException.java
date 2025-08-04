package dk.kavv.uuideck.utils;

public class ValueExceedsRadixException extends RuntimeException {
    public ValueExceedsRadixException(int val, int radix) {
        super(String.format("Value %d cannot equal or exceed radix/base %d", val, radix));
    }
}
