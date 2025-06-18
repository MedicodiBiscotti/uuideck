package dk.kavv.uuideck.utils;

import java.math.BigInteger;

public class FactoradicUtils {
    private FactoradicUtils() {
    }

    public static BigInteger factorial(int num) {
        if (num < 0) {
            throw new NegativeInputException();
        }
        // 0 and 1 return 1 without looping
        BigInteger res = BigInteger.ONE;
        while (num > 1) {
            res = res.multiply(BigInteger.valueOf(num--));
        }
        return res;
    }
}
