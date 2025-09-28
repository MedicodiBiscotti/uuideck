package dk.kavv.uuideck.decks;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TarotDeck implements SetSpec {
    @Override
    public int getLength() {
        return 52;
    }

    @Override
    public String present(byte[] bytes) {
        // Alternatively, StringJoiner and for loop.
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> elementName(bytes[i]))
                .collect(Collectors.joining(", "));
    }

    @Override
    public String elementName(byte b) {
        int suit = b / 13;
        int rank = b % 13;
        String res = switch (rank) {
            case 0 -> "A";
            case 10 -> "J";
            case 11 -> "Q";
            case 12 -> "K";
            default -> String.valueOf(rank + 1);
        };
        res += switch (suit) {
            case 0 -> "S";
            case 1 -> "C";
            case 2 -> "D";
            case 3 -> "H";
            default -> throw new InvalidDeckException(b, suit);
        };
        return res;
    }
}
