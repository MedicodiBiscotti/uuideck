package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RunningIntegerDeckTest {
    private final RunningIntegerDeck deckGenerator = new RunningIntegerDeck();

    @Test
    void generateWithSeedReturnsDeterminedDeck() {
        SetSpec spec = new FrenchSuitedDeck();
        byte[] deck = deckGenerator.generate(spec, new Random(0));
        byte[] expected = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};
        assertArrayEquals(expected, deck);
    }
}