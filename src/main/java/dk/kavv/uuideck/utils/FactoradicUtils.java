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

    public static byte[] decimalToFactoradic(BigInteger decimal, int length) {
        byte[] res = new byte[length];
        BigInteger quotient = decimal;
        // Could also loop while decimal > 0 and add to a growing List. No need for length, then.
        for (int i = res.length - 1; i >= 0; i--) {
            // Short circuit if decimal is 0. All future quotients and remainders are also 0.
            if (quotient.equals(BigInteger.ZERO)) {
                break;
            }
            BigInteger radix = BigInteger.valueOf(res.length - i);
            BigInteger[] quotientAndRemainder = quotient.divideAndRemainder(radix);
            quotient = quotientAndRemainder[0]; // Reassign the new quotient
            BigInteger remainder = quotientAndRemainder[1];
            res[i] = remainder.byteValue();
        }
        // Could also check at beginning if decimal >= factorial(length). Would catch earlier, but do more compute.
        // If you don't need to hold on to original decimal value for this error message, we can reassign quotient to decimal in the loop.
        if (!quotient.equals(BigInteger.ZERO)) {
            throw new ValueExceedsLengthException(decimal, length);
        }
        return res;
    }

    /**
     * Constructs Lehmer code for a given permutation by looping through it and decrementing all remaining values if they are greater than the current value.
     * Another way to think of it is shifting their index as a response to a lower value being removed from the pool of future choices.
     *
     * @param perm Permutation to encode
     * @return Lehmer code
     */
    public static byte[] encodeLehmer(byte[] perm) {
        byte[] lehmer = perm.clone();
        for (int i = 0; i < lehmer.length - 1; i++) {
            for (int j = i + 1; j < lehmer.length; j++) {
                if (lehmer[j] > lehmer[i]) {
                    lehmer[j]--;
                }
            }
        }
        return lehmer;
    }

    public static byte[] decodeLehmer(byte[] lehmer) {
        // Don't really need to preserve original data when decoding, but might as well.
        byte[] perm = lehmer.clone();
        for (int i = perm.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < perm.length; j++) {
                if (perm[j] >= perm[i]) {
                    perm[j]++;
                }
            }
        }
        return perm;
    }

    /*
 Can also be done like this:
 Finds the right position for the value/index i based on lehmer[i] (how many elements are smaller later in perm).
 Moves other elements left to make room,  then inserts there.
    public int[] decodeLehmer(int[] lehmer) {
        int n = lehmer.length;
        int[] perm = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int pos = i + lehmer[i];
            // Shift elements right
            for (int j = i; j < pos; j++) {
                perm[j] = perm[j + 1];
            }
            perm[pos] = i;
        }
        return perm;
    }
*/

}
