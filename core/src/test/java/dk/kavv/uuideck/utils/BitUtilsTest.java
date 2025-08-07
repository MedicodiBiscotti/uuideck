package dk.kavv.uuideck.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitUtilsTest {
    @Test
    void bitLengthOf0Is0() {
        assertEquals(0, BitUtils.bitLengthOfNumber(0));
    }

    @Test
    void bitLengthOf1Is1() {
        assertEquals(1, BitUtils.bitLengthOfNumber(1));
    }

    @Test
    void bitLengthOf31Is5() {
        assertEquals(5, BitUtils.bitLengthOfNumber(31));
    }

    @Test
    void bitLengthOf32Is6() {
        assertEquals(6, BitUtils.bitLengthOfNumber(32));
    }

    @Test
    void bitLengthOf51Is6() {
        assertEquals(6, BitUtils.bitLengthOfNumber(51));
    }

    @Test
    void bitLengthOf71Is7() {
        assertEquals(7, BitUtils.bitLengthOfNumber(71));
    }
}