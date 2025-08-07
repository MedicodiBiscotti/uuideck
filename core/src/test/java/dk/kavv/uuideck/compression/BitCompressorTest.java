package dk.kavv.uuideck.compression;

import dk.kavv.uuideck.decks.CustomLengthSet;
import dk.kavv.uuideck.decks.FrenchSuitedDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BitCompressorTest {

    @Test
    void compress4NumbersWith6Bit() {
        BitCompressor compressor = new BitCompressor(new FrenchSuitedDeck());
        // Exploits a quirk of chopping off leading 0's in LE.
        byte[] in = {1, 1, 1, 1};
        byte[] out = compressor.compress(in);
        byte[] expected = {65, 16, 4};
        assertArrayEquals(expected, out);
    }

    @Test
    void decompress4NumbersWith6Bit() {
        BitCompressor compressor = new BitCompressor(new FrenchSuitedDeck());
        byte[] in = {65, 16, 4};
        byte[] out = compressor.decompress(in);
        byte[] expected = {1, 1, 1, 1,
                // To compensate for the hack above.
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, out);
    }

    @Test
    void compress4NumbersWith2Bit() {
        BitCompressor compressor = new BitCompressor(new CustomLengthSet(4));
        // Exploits a quirk of chopping off leading 0's in LE.
        byte[] in = {1, 1, 1, 1};
        byte[] out = compressor.compress(in);
        byte[] expected = {85};
        assertArrayEquals(expected, out);
    }

    @Test
    void decompress4NumbersWith2Bit() {
        BitCompressor compressor = new BitCompressor(new CustomLengthSet(4));
        byte[] in = {85};
        byte[] out = compressor.decompress(in);
        byte[] expected = {1, 1, 1, 1};
        assertArrayEquals(expected, out);
    }
}