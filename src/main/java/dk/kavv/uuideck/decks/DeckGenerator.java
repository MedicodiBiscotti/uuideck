package dk.kavv.uuideck.decks;

import java.util.Random;

public interface DeckGenerator {
    byte[] generate(SetSpec spec, Random r);
}
