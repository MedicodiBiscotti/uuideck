package dk.kavv.uuideck.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactoradicUtils {
    public static final int DECK_SIZE = 52;
    // Can also prepopulate entire cache in static block or instantiation.
    public static final ArrayList<BigInteger> FACTORIAL_CACHE = new ArrayList<>(List.of(BigInteger.ONE));

    private FactoradicUtils() {
    }

    public static BigInteger factorial(int num) {
        if (num < 0) {
            throw new NegativeInputException(num);
        }
        while (FACTORIAL_CACHE.size() <= num) {
            int i = FACTORIAL_CACHE.size();
            // Start from highest, cache next value
            FACTORIAL_CACHE.add(FACTORIAL_CACHE.get(i - 1).multiply(BigInteger.valueOf(i)));
        }
        return FACTORIAL_CACHE.get(num);
    }

    // Takes and returns byte[] for convenience, so it can be encoded as data without having to convert an entire array.
    public static BigInteger factoradicToDecimal(byte[] factoradic) {
        // Includes trailing 0 in factoradic because of validation.
        BigInteger res = BigInteger.ZERO;
        for (int i = 0; i < factoradic.length; i++) {
            byte val = factoradic[i];
            int radix = factoradic.length - i;
            if (val >= radix) {
                throw new ValueExceedsRadixException(val, radix);
            } else if (val < 0) {
                throw new NegativeInputException(val);
            }
            res = res.add(BigInteger.valueOf(val).multiply(factorial(radix - 1)));
        }
        return res;
    }

    public static byte[] decimalToFactoradic(BigInteger decimal) {
        return null;
    }
}
