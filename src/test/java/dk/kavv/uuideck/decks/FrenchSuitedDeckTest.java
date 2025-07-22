package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FrenchSuitedDeckTest {
    private final FrenchSuitedDeck deck = new FrenchSuitedDeck();

    @Test
    void lowestValueToCard() {
        assertEquals("AS", deck.elementName((byte) 0));
    }

    @Test
    void lowestValueOfHigherSuitToCard() {
        assertEquals("AC", deck.elementName((byte) 13));
    }

    @Test
    void highestValueToCard() {
        assertEquals("KH", deck.elementName((byte) 51));
    }

    @Test
    @Disabled("Not sure I want to implement a guard for this until necessary")
    void negativeValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            deck.elementName((byte) -1);
        });
    }

    @Test
    void tooHighValueThrowsException() {
        assertThrows(InvalidDeckException.class, () -> {
            deck.elementName((byte) 52);
        });
    }

}