package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.compression.SixBitCompressor;
import dk.kavv.uuideck.decks.FrenchSuitedDeck;
import dk.kavv.uuideck.decks.SetSpec;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EncoderFactoryTest {
    @Disabled
    @Test
    void givenNullInputsThenReturnBase64Compressed() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), null, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenNullInputsThenReturnMulti() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), null, spec);
        assertInstanceOf(MultiEncoder.class, encoder);
        assertEquals(4, ((MultiEncoder) encoder).getEncoders().size());
    }

    @Test
    void givenAsciiAndCompressorThenThrowException() {
        SetSpec spec = new FrenchSuitedDeck();
        IncompatibleComponentsException e = assertThrows(IncompatibleComponentsException.class, () -> {
            EncoderFactory.getEncoder(Optional.of(true), EncoderType.ascii, spec);
        });
        assertEquals(1, e.getErrors().size());
    }

    @Test
    void givenAsciiThenReturnAscii() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), EncoderType.ascii, spec);
        assertInstanceOf(AsciiEncoder.class, encoder);
    }

    @Test
    void givenBase64ThenReturnBase64WithCompression() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(Optional.empty(), EncoderType.base64, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenBase64AndCompressionThenReturnBase64WithCompression() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(Optional.of(true), EncoderType.base64, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(SixBitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenBase64AndNoCompressionThenReturnBase64WithoutCompression() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(Optional.of(false), EncoderType.base64, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(NoOpCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }
}