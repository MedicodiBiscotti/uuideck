package dk.kavv.uuideck.compression;

import dk.kavv.uuideck.decks.SetSpec;
import dk.kavv.uuideck.utils.BitUtils;
import lombok.RequiredArgsConstructor;

import java.util.BitSet;

@RequiredArgsConstructor
public class BitCompressor implements Compressor {
    private final SetSpec spec;

    /*
    BitSet both produces and reads as little-endian. As long as a BitSet or other LE parsing is used, this is fine.
    Can also be implemented with two BitSets of different lengths and indices, or String manipulation.

    If deck ends in 8 0's (rare), they'll get chopped off. Result array is then 1 short.
    As long as decompress knows length, it should still work.
     */
    @Override
    public byte[] compress(byte[] deck) {
        int bitLength = BitUtils.bitLengthOfNumber(spec.getLength() - 1);
        int totalBitLength = spec.getLength() * bitLength;
        BitSet bits = new BitSet(totalBitLength);
        int start = 0;
        for (int card : deck) {
            for (int i = 0; i < bitLength; i++) {
                if (((card >> i) & 1) == 1) {
                    bits.set(start + i);
                }
            }
            start += bitLength;
        }
        return bits.toByteArray();
    }

    @Override
    public byte[] decompress(byte[] compressed) {
        int bitLength = BitUtils.bitLengthOfNumber(spec.getLength() - 1);
        BitSet bits = BitSet.valueOf(compressed);
        // Doesn't work if values eventually exceed 1 byte.
        // ~~ Or do maths with ratio of bits, e.g. for 52 (6 bit): compressed.length * 4 / 3 ~~
        // ^ Doesn't work in rare cases where leading 0's are removed leading to wrong length.
        byte[] deck = new byte[spec.getLength()];
        int start = 0;
        for (int i = 0; i < deck.length; i++) {
            byte val = 0;
            for (int j = 0; j < bitLength; j++) {
                if (bits.get(start + j)) {
                    val |= (byte) (1 << j);
                }
            }
            deck[i] = val;
            start += bitLength;
        }
        return deck;
    }
}
