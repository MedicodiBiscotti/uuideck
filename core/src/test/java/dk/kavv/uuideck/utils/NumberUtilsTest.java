package dk.kavv.uuideck.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberUtilsTest {


    @Test
    void given0WhenPadToGroupOfThreeThenPad2() {
        assertEquals("000", NumberUtils.padToGroupOfThree(0));
    }

    @Test
    void given10WhenPadToGroupOfThreeThenPad1() {
        assertEquals("010", NumberUtils.padToGroupOfThree(10));
    }

    @Test
    void given1WhenPadToGroupOfThreeThenPad2() {
        assertEquals("001", NumberUtils.padToGroupOfThree(1));
    }

    @Test
    void given100WhenPadToGroupOfThreeThenPad0() {
        assertEquals("100", NumberUtils.padToGroupOfThree(100));
    }

    @Test
    void given1000WhenPadToGroupOfThreeThenPad2() {
        assertEquals("001000", NumberUtils.padToGroupOfThree(1_000));
    }

    @Test
    void given10000WhenPadToGroupOfThreeThenPad1() {
        assertEquals("010000", NumberUtils.padToGroupOfThree(10_000));
    }

    @Test
    void given100000WhenPadToGroupOfThreeThenPad0() {
        assertEquals("100000", NumberUtils.padToGroupOfThree(100_000));
    }

    @ParameterizedTest
    @CsvSource({"0,zero", "1,one", "2,two", "3,three", "4,four", "5,five", "6,six", "7,seven", "8,eight", "9,nine",
            "1001,one thousand one", "123456789,one hundred twenty three million four hundred fifty six thousand seven hundred eighty nine"
    })
    void givenNumberWhenToTextReturnName(long input, String expected) {
        assertEquals(expected, NumberUtils.toText(input));
    }
}