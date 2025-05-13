package dk.kavv.uuideck;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        byte[] bytes = generateDeck(new Random());
        System.out.println(Arrays.toString(bytes));
        presentDeck(bytes);
        String encoded = encode(bytes);
        System.out.println(encoded);
    }

    public static byte[] generateDeck(Random r) {
        List<Integer> deck = new ArrayList<>(IntStream.range(0, 52).boxed().toList());
        Collections.shuffle(deck, r);
        byte[] bytes = new byte[deck.size()];
        for (int i = 0; i < deck.size(); i++) {
            bytes[i] = deck.get(i).byteValue();
        }
        return bytes;
    }

    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static void presentDeck(byte[] bytes) {
        // Alternatively, StringJoiner and for loop.
        System.out.println(
                IntStream.range(0, bytes.length)
                        .mapToObj(i -> toCard(bytes[i]))
                        .collect(Collectors.joining(", ")));
    }

    public static String toCard(byte b) {
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
            default -> throw new IllegalStateException("Unexpected value: " + suit);
        };
        return res;
    }
}
