package dk.kavv.uuideck.compression;

import java.util.BitSet;

public class BitCompressor implements Compressor {
    /*
    BitSet both produces and reads as little-endian. As long as a BitSet or other LE parsing is used, this is fine.
    Can also be implemented with two BitSets of different lengths and indices, or String manipulation.
     */
    @Override
    public byte[] compress(byte[] deck) {
        BitSet bits = new BitSet(312); // Init size 52 * 6
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

    @Override
    public byte[] decompress(byte[] compressed) {
        BitSet bits = BitSet.valueOf(compressed);
        byte[] deck = new byte[compressed.length * 4 / 3];
        int start = 0;
        for (int i = 0; i < deck.length; i++) {
            byte val = 0;
            for (int j = 0; j < 6; j++) {
                if (bits.get(start + j)) {
                    val |= (byte) (1 << j);
                }
            }
            deck[i] = val;
            start += 6;
        }
        return deck;
    }
}
