package dk.kavv.uuideck.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactoradicUtilsTest {
    @Test
    void factorialNegativeThrowsError() {
        NegativeInputException e = assertThrows(NegativeInputException.class, () -> FactoradicUtils.factorial(-1));
        assertEquals("Value -1 cannot be negative", e.getMessage());
    }

    @Test
    void factorialZeroReturnsOne() {
        assertEquals(BigInteger.ONE, FactoradicUtils.factorial(0));
    }


    @Test
    void factorialOneReturnsOne() {
        assertEquals(BigInteger.ONE, FactoradicUtils.factorial(1));
    }

    @Test
    void factorialTwoReturnsTwo() {
        assertEquals(BigInteger.TWO, FactoradicUtils.factorial(2));
    }

    @Test
    void factorialThreeReturnsSix() {
        assertEquals(BigInteger.valueOf(6), FactoradicUtils.factorial(3));
    }

    @Test
    void factorialFiftyTwoReturnsLarge() {
        assertEquals(new BigInteger("80658175170943878571660636856403766975289505440883277824000000000000"), FactoradicUtils.factorial(52));
    }

    @Test
    void factoradic012Returns5() {
        byte[] factoradic = {2, 1, 0};
        assertEquals(BigInteger.valueOf(5), FactoradicUtils.factoradicToDecimal(factoradic));
    }

    @Test
    void factoradicMaxDeckReturnsFactorialMinusOne() {
        List<Integer> ints = IntStream.iterate(51, value -> value >= 0, operand -> operand - 1).boxed().toList();
        byte[] factoradic = new byte[ints.size()];
        for (int i = 0; i < ints.size(); i++) {
            factoradic[i] = ints.get(i).byteValue();
        }
        assertEquals(new BigInteger("80658175170943878571660636856403766975289505440883277823999999999999"), FactoradicUtils.factoradicToDecimal(factoradic));
    }

    @Test
    void factoradicZeroReturnsZero() {
        byte[] factoradic = new byte[52];
        assertEquals(BigInteger.ZERO, FactoradicUtils.factoradicToDecimal(factoradic));
    }

    @Test
    void invalidFactoradicEqualsThrowsError() {
        byte[] factoradic = {2, 2, 0};
        ValueExceedsRadixException e = assertThrows(ValueExceedsRadixException.class, () -> FactoradicUtils.factoradicToDecimal(factoradic));
        assertEquals("Value 2 cannot equal or exceed radix/base 2", e.getMessage());
    }

    @Test
    void invalidFactoradicExceedsThrowsError() {
        byte[] factoradic = {2, 3, 0};
        ValueExceedsRadixException e = assertThrows(ValueExceedsRadixException.class, () -> FactoradicUtils.factoradicToDecimal(factoradic));
        assertEquals("Value 3 cannot equal or exceed radix/base 2", e.getMessage());
    }

    @Test
    void invalidFactoradicNegativeThrowsError() {
        byte[] factoradic = {2, -1, 0};
        NegativeInputException e = assertThrows(NegativeInputException.class, () -> FactoradicUtils.factoradicToDecimal(factoradic));
        assertEquals("Value -1 cannot be negative", e.getMessage());
    }
}