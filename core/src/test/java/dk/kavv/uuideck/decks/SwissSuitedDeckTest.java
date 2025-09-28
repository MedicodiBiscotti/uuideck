package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SwissSuitedDeckTest {
    private final SwissSuitedDeck deck = new SwissSuitedDeck();

    @Test
    void lengthIs52() {
        assertEquals(36, deck.getLength());
    }

    @Test
    void lowestValueToCard() {
        assertEquals("AR", deck.elementName((byte) 0));
    }

    @Test
    void lowestValueOfHigherSuitToCard() {
        assertEquals("AA", deck.elementName((byte) 9));
    }

    @Test
    void highestValueToCard() {
        assertEquals("KS", deck.elementName((byte) 35));
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
            deck.elementName((byte) 36);
        });
    }

}