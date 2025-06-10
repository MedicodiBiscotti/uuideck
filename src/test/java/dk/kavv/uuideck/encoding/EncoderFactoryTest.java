package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.compression.SixBitCompressor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncoderFactoryTest {
    @Disabled
    @Test
    void givenNullInputsThenReturnBase64Compressed() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), null);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenNullInputsThenReturnMulti() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), null);
        assertInstanceOf(MultiEncoder.class, encoder);
        assertEquals(3, ((MultiEncoder) encoder).getEncoders().size());
    }

    @Test
    void givenAsciiAndCompressorThenThrowException() {
        IncompatibleComponentsException e = assertThrows(IncompatibleComponentsException.class, () -> {
            EncoderFactory.getEncoder(Optional.of(true), EncoderType.ascii);
        });
        assertEquals(1, e.getErrors().size());
    }

    @Test
    void givenAsciiThenReturnAscii() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), EncoderType.ascii);
        assertInstanceOf(AsciiEncoder.class, encoder);
    }

    @Test
    void givenBase64ThenReturnBase64WithCompression() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), EncoderType.base64);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenBase64AndCompressionThenReturnBase64WithCompression() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.of(true), EncoderType.base64);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenBase64AndNoCompressionThenReturnBase64WithoutCompression() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.of(false), EncoderType.base64);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(NoOpCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }
}