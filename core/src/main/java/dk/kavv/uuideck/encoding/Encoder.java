package dk.kavv.uuideck.encoding;

public interface Encoder {
    String encode(byte[] bytes);

    byte[] decode(String s);
}
