package dk.kavv.uuideck.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSeedGeneratorTest {
    private final StringSeedGenerator sg = new StringSeedGenerator();

    @Test
    void emptyStringReturns0() {
        assertEquals(0, sg.generate(""));
    }

    @Test
    void oneCharReturnsAsciiValue() {
        assertEquals(65, sg.generate("A"));
    }

    @Test
    void twoCharsReturnsConcatenatedBytes() {
        assertEquals(16705, sg.generate("AA"));
    }

    @Test
    void twoDifferentChars() {
        // LE vs. BE makes a difference here. Little vs. big end byte first.
        assertEquals(16961, sg.generate("AB"));
    }

    @Test
    void eightChars() {
        assertEquals(4702111234474983745L, sg.generate("AAAAAAAA"));
    }

    @Test
    void allowsOverflow() {
        assertEquals(4702111234474983745L, sg.generate("AAAAAAAAA"));
    }

    @Test
    void overflowCutsOffEnd() {
        assertEquals(4702111234474983745L, sg.generate("AAAAAAAAB"));
    }

}