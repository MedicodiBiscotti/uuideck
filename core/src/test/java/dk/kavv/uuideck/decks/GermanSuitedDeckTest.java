package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GermanSuitedDeckTest {
    private final GermanSuitedDeck deck = new GermanSuitedDeck();

    @Test
    void lengthIs52() {
        assertEquals(32, deck.getLength());
    }

    @Test
    void lowestValueToCard() {
        assertEquals("AL", deck.elementName((byte) 0));
    }

    @Test
    void lowestValueOfHigherSuitToCard() {
        assertEquals("AA", deck.elementName((byte) 8));
    }

    @Test
    void highestValueToCard() {
        assertEquals("KH", deck.elementName((byte) 31));
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
            deck.elementName((byte) 32);
        });
    }

}