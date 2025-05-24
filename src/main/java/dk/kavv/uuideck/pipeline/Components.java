package dk.kavv.uuideck.pipeline;

import dk.kavv.uuideck.decks.DeckGenerator;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.encoding.SixBitCompressor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public class Components {
    private final DeckGenerator deckGenerator;
    // Unsure if Compressor should be a List<Transformer> or Optional or just null.
    private final Optional<SixBitCompressor> compressor;
    private final Encoder encoder;
}
