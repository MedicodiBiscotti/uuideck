package dk.kavv.uuideck.encoding;

import java.util.BitSet;

public class SixBitCompressor {
    /*
    BitSet both produces and reads as little-endian. As long as a BitSet or other LE parsing is used, this is fine.
    Can also be implemented with two BitSets of different lengths and indices, or String manipulation.
     */
    public byte[] compress(byte[] deck) {
        BitSet bits = new BitSet(312);
        int start = 0;
        for (int card : deck) {
            for (int i = 0; i < 6; i++) {
                if (((card >> i) & 1) == 1) {
                    bits.set(start + i);
                }
            }
            start += 6;
        }
        return bits.toByteArray();
    }
}
