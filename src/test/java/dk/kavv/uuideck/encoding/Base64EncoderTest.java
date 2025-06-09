package dk.kavv.uuideck.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Base64EncoderTest {
    private final Base64Encoder encoder = new Base64Encoder(new Compressor() {
    });

    @Test
    void encode() {
        byte[] bytes = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};
        String actual = encoder.encode(bytes);
        assertEquals("LxgZJA8rMwkXBSISMAofHicpKhQlLgEMBw0mKBMOBgggAwAEGxEyIRAxGi0VIxYLAh0cLA==", actual);
    }

    @Test
    void decode() {
        String in = "LxgZJA8rMwkXBSISMAofHicpKhQlLgEMBw0mKBMOBgggAwAEGxEyIRAxGi0VIxYLAh0cLA==";
        byte[] expected = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};
        byte[] actual = encoder.decode(in);
        assertArrayEquals(expected, actual);
    }
}