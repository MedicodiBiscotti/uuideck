package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.Compressor;
import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.compression.SixBitCompressor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Constructs the right encoder setup based on input options.
 * <p>
 * Provides sensible defaults if null is given.
 */
public class EncoderFactory {
    private static final List<String> errors = new ArrayList<>();

    public static Encoder getEncoder(Optional<Boolean> doCompression, EncoderType encoderType) {
        errors.clear();
        Encoder encoder = (encoderType != null) ? switch (encoderType) {
            case base64 -> getBase64(doCompression);
            case ascii -> getAscii(doCompression);
            case all -> getMulti(doCompression);
        } : getMulti(doCompression);
        if (!errors.isEmpty()) {
            throw new IncompatibleComponentsException(List.copyOf(errors));
        }
        return encoder;
    }

    public static MultiEncoder getMulti(Optional<Boolean> doCompression) {
        // For now, ignore boolean.
        return new MultiEncoder(List.of(
                new Base64Encoder(new NoOpCompressor()),
                new Base64Encoder(new SixBitCompressor()),
                new AsciiEncoder()
        ));
    }

    /*
    Checks the component in question against previously decided components. Not the requested option, but what was decided considering defaults.
    Order in which components are decided depend on ease of use. Which are most likely to be specified as options? Which are most compatible?
    Compressor only has 1 option, so it's a boolean choice that can be inferred from the other choices.

    This is no longer accurate, but I liked that pipeline too.
     */

    public static Base64Encoder getBase64(Optional<Boolean> doCompression) {
        Compressor compressor = (doCompression.isEmpty() || doCompression.get()) ? new SixBitCompressor() : new NoOpCompressor();
        return new Base64Encoder(compressor);
    }

    public static AsciiEncoder getAscii(Optional<Boolean> doCompression) {
        if (doCompression.isPresent() && doCompression.get()) {
            errors.add("6-bit compression incompatible with ASCII encoding");
        }
        return new AsciiEncoder();
    }
}
