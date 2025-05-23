package dk.kavv.uuideck.pipeline;

import dk.kavv.uuideck.encoding.AsciiEncoder;
import dk.kavv.uuideck.encoding.EightBitBase64Encoder;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.encoding.EncoderType;

public class PipelineFactory {
    public static Encoder getEncoder(EncoderType encoderType) {
        return encoderType != null ? switch (encoderType) {
            case base64 -> new EightBitBase64Encoder();
            case ascii -> new AsciiEncoder();
        } : new EightBitBase64Encoder();
    }
}
