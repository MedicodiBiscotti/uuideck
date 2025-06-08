package dk.kavv.uuideck.encoding;

/**
 * Compresses data. Default implementation is no-op.
 */
public interface Compressor {
    default byte[] compress(byte[] deck) {
        return deck;
    }

    default byte[] decompress(byte[] compressed) {
        return compressed;
    }
}
