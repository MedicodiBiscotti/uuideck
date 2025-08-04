package dk.kavv.uuideck.decks;

import lombok.Getter;

@Getter
public class InvalidDeckException extends RuntimeException {
    private final byte value;
    private final int suit;

    public InvalidDeckException(byte value, int suit) {
        super("Card value is invalid");
        this.value = value;
        this.suit = suit;
    }
}
