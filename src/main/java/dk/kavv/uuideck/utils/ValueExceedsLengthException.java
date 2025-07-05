package dk.kavv.uuideck.utils;

import java.math.BigInteger;

public class ValueExceedsLengthException extends RuntimeException {
    public ValueExceedsLengthException(BigInteger val, int length) {
        super(String.format("Value %d cannot fit in array of length %d", val, length));
    }
}
