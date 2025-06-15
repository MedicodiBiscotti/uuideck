package dk.kavv.uuideck.decks;

import dk.kavv.uuideck.errorhandling.ShortException;
import lombok.Getter;

@Getter
public class InvalidDeckException extends RuntimeException implements ShortException {
    private final byte value;
    private final int suit;

    public InvalidDeckException(byte value, int suit) {
        super("Card value is invalid");
        this.value = value;
        this.suit = suit;
    }
}
