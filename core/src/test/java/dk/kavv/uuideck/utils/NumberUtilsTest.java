package dk.kavv.uuideck.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

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
            "13,thirteen", "19,nineteen", "20,twenty", "21,twenty-one", "25,twenty-five",
            "115,one hundred fifteen", "666,six hundred sixty-six",
            "1001,one thousand one", "123456789,one hundred twenty-three million four hundred fifty-six thousand seven hundred eighty-nine",
            "9223372036854775807,nine quintillion two hundred twenty-three quadrillion three hundred seventy-two trillion thirty-six billion eight hundred fifty-four million seven hundred seventy-five thousand eight hundred seven",
    })
    void givenNumberWhenToTextReturnName(long input, String expected) {
        assertEquals(expected, NumberUtils.toText(input));
    }

    @Test
    void given52FactorialWhenToTextReturnName() {
        assertEquals(
                "eighty unvigintillion six hundred fifty-eight vigintillion one hundred seventy-five novemdecillion one hundred seventy octodecillion nine hundred forty-three septendecillion eight hundred seventy-eight sexdecillion five hundred seventy-one quindecillion six hundred sixty quattuordecillion six hundred thirty-six tredecillion eight hundred fifty-six duodecillion four hundred three undecillion seven hundred sixty-six decillion nine hundred seventy-five nonillion two hundred eighty-nine octillion five hundred five septillion four hundred forty sextillion eight hundred eighty-three quintillion two hundred seventy-seven quadrillion eight hundred twenty-four trillion",
                NumberUtils.toText(new BigInteger("80658175170943878571660636856403766975289505440883277824000000000000")));
    }
}