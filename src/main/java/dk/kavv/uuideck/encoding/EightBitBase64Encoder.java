package dk.kavv.uuideck.encoding;

import java.util.Base64;

public class EightBitBase64Encoder implements Encoder {
    public String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] decode(String s) {
        return Base64.getDecoder().decode(s);
    }
}
