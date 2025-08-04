package dk.kavv.uuideck.compression;

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
