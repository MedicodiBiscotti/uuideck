package dk.kavv.uuideck.decks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RunningIntegerDeck implements DeckGenerator {
    public byte[] generate(Random r) {
        List<Integer> deck = new ArrayList<>(IntStream.range(0, 52).boxed().toList());
        Collections.shuffle(deck, r);
        byte[] bytes = new byte[deck.size()];
        for (int i = 0; i < deck.size(); i++) {
            bytes[i] = deck.get(i).byteValue();
        }
        return bytes;
    }

    public void present(byte[] bytes) {
        // Alternatively, StringJoiner and for loop.
        System.out.println(
                IntStream.range(0, bytes.length)
                        .mapToObj(i -> toCard(bytes[i]))
                        .collect(Collectors.joining(", ")));
    }

    public String toCard(byte b) {
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
            default -> throw new IllegalArgumentException("Unexpected value: " + suit);
        };
        return res;
    }
}
