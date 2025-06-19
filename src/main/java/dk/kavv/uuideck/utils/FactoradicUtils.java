package dk.kavv.uuideck.utils;

import java.math.BigInteger;

public class FactoradicUtils {
    private FactoradicUtils() {
    }

    public static BigInteger factorial(int num) {
        if (num < 0) {
            throw new NegativeInputException(num);
        }
        // 0 and 1 return 1 without looping
        BigInteger res = BigInteger.ONE;
        while (num > 1) {
            res = res.multiply(BigInteger.valueOf(num--));
        }
        return res;
    }

    // Takes and returns byte[] for convenience, so it can be encoded as data without having to convert an entire array.
    public static BigInteger factoradicToDecimal(byte[] factoradic) {
        // Assumes trailing 0 in factoradic.
        // Because of Fisher-Yates, the factoradic is back to front.
        BigInteger res = BigInteger.ZERO;
        for (int i = factoradic.length; i > 1; i--) {
            byte val = factoradic[i - 1];
            if (val >= i) {
                throw new ValueExceedsRadixException(val, i);
            } else if (val < 0) {
                throw new NegativeInputException(val);
            }
            res = res.add(factorial(i - 1).multiply(BigInteger.valueOf(val)));
        }
        return res;
    }
}
