package dk.kavv.uuideck.encoding;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("Disabled until we make base ASCII value a variable")
class AsciiEncoderAfterSpaceTest {
    private final AsciiEncoder encoder = new AsciiEncoder();

    @Test
    void encode() {
        byte[] bytes = {0, 1, 2, 3};
        String actual = encoder.encode(bytes);
        String expected = "!\"#$";
        assertEquals(expected, actual);
    }

    @Test
    void decode() {
        String in = "!\"#$";
        byte[] actual = encoder.decode(in);
        byte[] expected = {0, 1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void encodeWholeDeck() {
        byte[] bytes = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};
        String actual = encoder.encode(bytes);
        String expected = "P9:E0LT*8&C3Q+@?HJK5FO\"-(.GI4/')A$!%<2SB1R;N6D7,#>=M";
        assertEquals(expected, actual);
    }

    @Test
    void decodeWholeDeck() {
        String in = "P9:E0LT*8&C3Q+@?HJK5FO\"-(.GI4/')A$!%<2SB1R;N6D7,#>=M";
        byte[] actual = encoder.decode(in);
        byte[] expected = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};
        assertArrayEquals(expected, actual);
    }
}