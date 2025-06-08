package dk.kavv.uuideck.pipeline;

import dk.kavv.uuideck.decks.RunningIntegerDeck;
import dk.kavv.uuideck.encoding.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Constructs the components of deck generation, transformation like bit-compression, and encoding based on input options.
 * <p>
 * Provides sensible defaults for the type of deck if null is given.
 */
public class ComponentsFactory {
    private static final List<String> errors = new ArrayList<>();

    public static Components getComponents(Optional<Boolean> doCompression, EncoderType encoderType) {
        Encoder encoder = getEncoder(encoderType);
        Compressor compressor = getCompressor(doCompression, encoder);
        if (!errors.isEmpty()) {
            throw new IncompatibleComponentsException(errors);
        }
        return new Components(new RunningIntegerDeck(), compressor, encoder);
    }

    /*
    Checks the component in question against previously decided components. Not the requested option, but what was decided considering defaults.
    Order in which components are decided depend on ease of use. Which are most likely to be specified as options? Which are most compatible?
    Compressor only has 1 option, so it's a boolean choice that can be inferred from the other choices.
     */

    public static Compressor getCompressor(Optional<Boolean> doCompression, Encoder encoder) {
        if (doCompression.isEmpty()) {
            if (encoder instanceof EightBitBase64Encoder) {
                return new SixBitCompressor();
            } else {
                return new Compressor() {
                };
            }
        }
        boolean b = doCompression.get();
        if (b) {
            if (encoder instanceof AsciiEncoder) {
                errors.add("6-bit compression incompatible with ASCII encoding");
                // Still return requested component, so the choice can be validated against other requested components.
            }
            return new SixBitCompressor();
        }
        return new Compressor() {
        };
    }

    public static Encoder getEncoder(EncoderType encoderType) {
        return encoderType != null ? switch (encoderType) {
            case base64 -> new EightBitBase64Encoder();
            case ascii -> new AsciiEncoder();
        } : new EightBitBase64Encoder();
    }
}
