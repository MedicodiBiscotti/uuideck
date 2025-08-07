package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.BitCompressor;
import dk.kavv.uuideck.compression.Compressor;
import dk.kavv.uuideck.compression.CompressorType;
import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.decks.SetSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Constructs the right encoder setup based on input options.
 * <p>
 * Provides sensible defaults if null is given.
 */
public class EncoderFactory {
    private static final List<String> errors = new ArrayList<>();

    // SetSpec is temporarily part of the encoding step until bigger refactor. Only needed for decimal decoding.
    public static Encoder getEncoder(CompressorType compressorType, EncoderType encoderType, SetSpec spec) {
        errors.clear();
        Encoder encoder = (encoderType != null) ? switch (encoderType) {
            case base64 -> getBase64(compressorType, spec);
            case ascii -> getAscii(compressorType);
            case decimal -> getDecimal(compressorType, spec);
            case all -> getMulti(compressorType, spec);
        } : getMulti(compressorType, spec);
        if (!errors.isEmpty()) {
            throw new IncompatibleComponentsException(List.copyOf(errors));
        }
        return encoder;
    }

    public static MultiEncoder getMulti(CompressorType compressorType, SetSpec spec) {
        // For now, ignore boolean.
        return new MultiEncoder(List.of(
                new Base64Encoder(new NoOpCompressor()),
                new Base64Encoder(new BitCompressor(spec)),
                new AsciiEncoder(),
                new DecimalEncoder(spec)
        ));
    }

    /*
    Checks the component in question against previously decided components. Not the requested option, but what was decided considering defaults.
    Order in which components are decided depend on ease of use. Which are most likely to be specified as options? Which are most compatible?
    Compressor only has 1 option, so it's a boolean choice that can be inferred from the other choices.

    This is no longer accurate, but I liked that pipeline too.
     */

    public static Base64Encoder getBase64(CompressorType compressorType, SetSpec spec) {
        Compressor compressor = (compressorType == null || compressorType == CompressorType.bit) ? new BitCompressor(spec) : new NoOpCompressor();
        return new Base64Encoder(compressor);
    }

    public static AsciiEncoder getAscii(CompressorType compressorType) {
        if (compressorType == CompressorType.bit) {
            errors.add("Bit compression incompatible with ASCII encoding");
        }
        return new AsciiEncoder();
    }

    public static DecimalEncoder getDecimal(CompressorType compressorType, SetSpec spec) {
        if (compressorType == CompressorType.bit) {
            errors.add("Bit compression incompatible with decimal encoding");
        }
        return new DecimalEncoder(spec);
    }
}
