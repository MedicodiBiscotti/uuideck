package dk.kavv.uuideck.decks;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GermanSuitedDeck implements SetSpec {
    @Override
    public int getLength() {
        return 32;
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
        int suit = b / 8;
        int rank = b % 8;
        String res = switch (rank) {
            case 0 -> "A";
            case 5 -> "U";
            case 6 -> "O";
            case 7 -> "K";
            default -> String.valueOf(rank + 6);
        };
        res += switch (suit) {
            case 0 -> "L";
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "H";
            default -> throw new InvalidDeckException(b, suit);
        };
        return res;
    }
}
