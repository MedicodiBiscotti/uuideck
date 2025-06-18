package dk.kavv.uuideck.utils;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactoradicUtilsTest {
    @Test
    void factorialNegativeThrowsError() {
        assertThrows(NegativeInputException.class, () -> FactoradicUtils.factorial(-1));
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
}