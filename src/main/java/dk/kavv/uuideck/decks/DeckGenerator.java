package dk.kavv.uuideck.decks;

import java.util.Random;

public interface DeckGenerator {
    byte[] generate(Random r);

    String present(byte[] bytes);

    String toCard(byte b);
}
