package dk.kavv.uuideck.encoding;

import java.nio.charset.StandardCharsets;

public class AsciiEncoder implements Encoder {
    private static final byte BASE_ASCII_VALUE = 33;

    @Override
    public String encode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] += BASE_ASCII_VALUE;
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public byte[] decode(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] -= BASE_ASCII_VALUE;
        }
        return bytes;
    }
}
