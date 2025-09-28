package dk.kavv.uuideck.decks;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SwissSuitedDeck implements SetSpec {
    @Override
    public int getLength() {
        return 36;
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
        int suit = b / 9;
        int rank = b % 9;
        String res = switch (rank) {
            case 0 -> "A";
            case 5 -> "B";
            case 6 -> "U";
            case 7 -> "O";
            case 8 -> "K";
            default -> String.valueOf(rank + 5);
        };
        res += switch (suit) {
            case 0 -> "R";
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "S";
            default -> throw new InvalidDeckException(b, suit);
        };
        return res;
    }
}
