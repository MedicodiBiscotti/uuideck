package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.BitCompressor;
import dk.kavv.uuideck.compression.CompressorType;
import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.decks.FrenchSuitedDeck;
import dk.kavv.uuideck.decks.SetSpec;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderFactoryTest {
    @Disabled
    @Test
    void givenNullInputsThenReturnBase64Compressed() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(null, null, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(BitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenNullInputsThenReturnMulti() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(null, null, spec);
        assertInstanceOf(MultiEncoder.class, encoder);
        assertEquals(4, ((MultiEncoder) encoder).getEncoders().size());
    }

    @Test
    void givenAsciiAndCompressorThenThrowException() {
        SetSpec spec = new FrenchSuitedDeck();
        IncompatibleComponentsException e = assertThrows(IncompatibleComponentsException.class, () -> {
            EncoderFactory.getEncoder(CompressorType.bit, EncoderType.ascii, spec);
        });
        assertEquals(1, e.getErrors().size());
    }

    @Test
    void givenAsciiThenReturnAscii() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(null, EncoderType.ascii, spec);
        assertInstanceOf(AsciiEncoder.class, encoder);
    }

    @Test
    void givenBase64ThenReturnBase64WithCompression() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(null, EncoderType.base64, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(BitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenBase64AndCompressionThenReturnBase64WithCompression() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(CompressorType.bit, EncoderType.base64, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(BitCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }

    @Test
    void givenBase64AndNoCompressionThenReturnBase64WithoutCompression() {
        SetSpec spec = new FrenchSuitedDeck();
        Encoder encoder = EncoderFactory.getEncoder(CompressorType.none, EncoderType.base64, spec);
        assertInstanceOf(Base64Encoder.class, encoder);
        assertInstanceOf(NoOpCompressor.class, ((Base64Encoder) encoder).getCompressor());
    }
}