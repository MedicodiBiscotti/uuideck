package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomSetTest {
    private CustomSet set = new CustomSet(List.of("a", "b", "c"));

    @Test
    void getLength() {
        assertEquals(3, set.getLength());
    }

    @Test
    void firstElementName() {
        assertEquals("a", set.elementName((byte) 0));
    }

    @Test
    void secondElementName() {
        assertEquals("b", set.elementName((byte) 1));
    }

    @Test
    void thirdElementName() {
        assertEquals("c", set.elementName((byte) 2));
    }
}