package dk.kavv.uuideck.encoding;

import java.nio.charset.StandardCharsets;

public class AsciiEncoder implements Encoder {
    /*
    33: Special characters after space
    40: Special characters after quotes
    48: Numbers (incl. some special characters)
    65: Letters
     */
    private static final byte BASE_ASCII_VALUE = 40;

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
