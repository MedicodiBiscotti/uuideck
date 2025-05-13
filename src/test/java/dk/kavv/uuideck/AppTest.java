package dk.kavv.uuideck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void init() {
    }

    @Test
    void stringToLongEmptyString() {
        assertEquals(0, App.stringToLong(""));
    }

    @Test
    void stringToLongOneChar() {
        assertEquals(65, App.stringToLong("A"));
    }

    @Test
    void stringToLongTwoChars() {
        assertEquals(16705, App.stringToLong("AA"));
    }

    @Test
    void stringToLongTwoDifferentChars() {
        // LE vs. BE makes a difference here. Little vs. big end byte first.
        assertEquals(16961, App.stringToLong("AB"));
    }

    @Test
    void stringToLongEightChars() {
        assertEquals(4702111234474983745L, App.stringToLong("AAAAAAAA"));
    }

    @Test
    void stringToLongAllowsOverflow() {
        assertEquals(4702111234474983745L, App.stringToLong("AAAAAAAAA"));
    }

    @Test
    void stringToLongOverflowCutsOffEnd() {
        assertEquals(4702111234474983745L, App.stringToLong("AAAAAAAAB"));
    }
}