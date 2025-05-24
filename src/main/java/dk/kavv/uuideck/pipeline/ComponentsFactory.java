package dk.kavv.uuideck.pipeline;

import dk.kavv.uuideck.encoding.AsciiEncoder;
import dk.kavv.uuideck.encoding.EightBitBase64Encoder;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.encoding.EncoderType;

/**
 * Constructs the components of deck generation, transformation like bit-compression, and encoding based on input options.
 * <p>
 * Provides sensible defaults for the type of deck if null is given.
 */
public class ComponentsFactory {
    public static Encoder getEncoder(EncoderType encoderType) {
        return encoderType != null ? switch (encoderType) {
            case base64 -> new EightBitBase64Encoder();
            case ascii -> new AsciiEncoder();
        } : new EightBitBase64Encoder();
    }
}
