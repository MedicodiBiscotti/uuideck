package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.compression.SixBitCompressor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MultiEncoderTest {
    @Test
    void givenMultipleEncodersReturnMultipleLines() {
        // Arrange
        Encoder encoder = new MultiEncoder(List.of(
                new Base64Encoder(new NoOpCompressor()),
                new Base64Encoder(new SixBitCompressor()),
                new AsciiEncoder()
        ));
        byte[] bytes = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};

        // Act
        String encoded = encoder.encode(bytes);

        // Assert
        String[] expected = {
                "Base64 (uncompressed):\tLxgZJA8rMwkXBSISMAofHicpKhQlLgEMBw0mKBMOBgggAwAEGxEyIRAxGi0VIxYLAh0cLA==",
                "Base64 (compressed):\tL5aRzzonVyFKsPJ5Z6pSpRswR2Oik2Mg4AAQWySHUKy11WgtQsex",
                "ASCII:\t\t\tW@AL7S[1?-J:X2GFOQR<MV)4/5NP;6.0H+(,C9ZI8YBU=K>3*EDT"
        };
        String[] actual = encoded.split(System.lineSeparator());
        assertArrayEquals(expected, actual);
    }
}