package dk.kavv.uuideck.decks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetSpecFactoryTest {
    @Test
    void whenFrenchReturnFrench() {
        assertInstanceOf(FrenchSuitedDeck.class, SetSpecFactory.getSpec(SetType.french, null, null));
    }

    @Test
    void whenCustomSetAndProvidedReturnCustomSet() {
        assertInstanceOf(CustomSet.class, SetSpecFactory.getSpec(SetType.customSet, List.of("a", "b", "c"), null));
    }

    @Test
    void whenCustomSetAndNullThrowError() {
        assertThrows(IllegalArgumentException.class, () -> SetSpecFactory.getSpec(SetType.customSet, null, null));
    }

    // Inverse, both must be supplied.
    @Test
    void whenCustomSetAndNullThrowError2() {
        assertThrows(IllegalArgumentException.class, () -> SetSpecFactory.getSpec(null, List.of("a", "b", "c"), null));
    }


    @Test
    void whenCustomLengthAndProvidedReturnCustomLength() {
        assertInstanceOf(CustomLengthSet.class, SetSpecFactory.getSpec(SetType.customLength, null, 3));
    }

    @Test
    void whenCustomLengthAndNullThrowError() {
        assertThrows(IllegalArgumentException.class, () -> SetSpecFactory.getSpec(SetType.customLength, null, null));
    }

    // Inverse, both must be supplied.
    @Test
    void whenCustomLengthAndNullThrowError2() {
        assertThrows(IllegalArgumentException.class, () -> SetSpecFactory.getSpec(null, null, 3));
    }
}