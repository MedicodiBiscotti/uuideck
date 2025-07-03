package dk.kavv.uuideck.decks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RunningIntegerDeck implements DeckGenerator {
    public byte[] generate(Random r) {
        List<Integer> deck = new ArrayList<>(IntStream.range(0, 52).boxed().toList());
        byte[] bytes = new byte[deck.size()];
        for (int i = 0; i < deck.size(); i++) {
            bytes[i] = deck.get(i).byteValue();
        }
        shuffle(bytes, r);
        return bytes;
    }

    /**
     * Perform a Fisher-Yates shuffle.
     *
     * @param deck
     * @param r
     */
    public void shuffle(byte[] deck, Random r) {
        // Could also do a List swap with `set` just like the Collections.shuffle implementation.
        // Or a List with actual removal instead of swaps. That would change the result of the picked indices.
        // We actually don't need to track indices which was the purpose of manually implementing the shuffle.
        // Instead, we can construct an actual Lehmer code from the final permutation.
        // That is also reversible without replaying deck generation with indices.
        for (int i = deck.length; i > 1; i--) {
            int j = r.nextInt(i);
            byte tmp = deck[i - 1];
            deck[i - 1] = deck[j];
            deck[j] = tmp;
        }
    }

    public String present(byte[] bytes) {
        // Alternatively, StringJoiner and for loop.
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> toCard(bytes[i]))
                .collect(Collectors.joining(", "));
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
            default -> throw new InvalidDeckException(b, suit);
        };
        return res;
    }
}
