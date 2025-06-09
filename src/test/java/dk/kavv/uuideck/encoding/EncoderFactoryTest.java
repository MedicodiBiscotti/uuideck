package dk.kavv.uuideck.encoding;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncoderFactoryTest {
    @Test
    void givenNullInputsThenReturnBase64Compressed() {
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), null);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
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
        assertFalse(((Base64Encoder) encoder).getCompressor() instanceof SixBitCompressor);
    }
}