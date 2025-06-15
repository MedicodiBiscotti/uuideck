package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.Compressor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Base64;

@Getter
@RequiredArgsConstructor
public class Base64Encoder implements Encoder {
    // Compression is only compatible with base64 encoding.
    // We need to optionally compress with multiple encodings,
    // so compression step is moved here.
    private final Compressor compressor;

    public String encode(byte[] bytes) {
        bytes = compressor.compress(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    public byte[] decode(String s) {
        try {
            byte[] bytes = Base64.getUrlDecoder().decode(s);
            return compressor.decompress(bytes);
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Not valid Base64", e);
        }
    }
}
