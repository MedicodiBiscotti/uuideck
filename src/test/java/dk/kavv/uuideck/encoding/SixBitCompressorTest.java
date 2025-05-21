package dk.kavv.uuideck.encoding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SixBitCompressorTest {
    private final SixBitCompressor compressor = new SixBitCompressor();

    @Test
    void compress() {
        byte[] in = {1, 1, 1, 1};
        byte[] out = compressor.compress(in);
        byte[] expected = {65, 16, 4};
        assertArrayEquals(expected, out);
    }

    @Test
    void decompress() {
        byte[] in = {65, 16, 4};
        byte[] out = compressor.decompress(in);
        byte[] expected = {1, 1, 1, 1};
        assertArrayEquals(expected, out);
    }
}