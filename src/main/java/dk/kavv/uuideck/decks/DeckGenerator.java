package dk.kavv.uuideck.decks;

import java.util.Random;

public interface DeckGenerator {
    byte[] generate(Random r);

    void present(byte[] bytes);

    String toCard(byte b);
}
