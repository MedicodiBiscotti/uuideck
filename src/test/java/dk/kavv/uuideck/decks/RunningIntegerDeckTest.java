package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RunningIntegerDeckTest {
    private final RunningIntegerDeck deckGenerator = new RunningIntegerDeck();

    @Test
    void generateWithSeedReturnsDeterminedDeck() {
        byte[] deck = deckGenerator.generate(new Random(0));
        byte[] expected = {47, 24, 25, 36, 15, 43, 51, 9, 23, 5, 34, 18, 48, 10, 31, 30, 39, 41, 42, 20, 37, 46, 1, 12, 7, 13, 38, 40, 19, 14, 6, 8, 32, 3, 0, 4, 27, 17, 50, 33, 16, 49, 26, 45, 21, 35, 22, 11, 2, 29, 28, 44};
        assertArrayEquals(expected, deck);
    }

    @Test
    void lowestValueToCard() {
        assertEquals("AS", deckGenerator.toCard((byte) 0));
    }

    @Test
    void lowestValueOfHigherSuitToCard() {
        assertEquals("AC", deckGenerator.toCard((byte) 13));
    }

    @Test
    void highestValueToCard() {
        assertEquals("KH", deckGenerator.toCard((byte) 51));
    }

    @Test
    @Disabled("Not sure I want to implement a guard for this until necessary")
    void negativeValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            deckGenerator.toCard((byte) -1);
        });
    }

    @Test
    void tooHighValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            deckGenerator.toCard((byte) 52);
        });
    }
}