package dk.kavv.uuideck;

import java.util.*;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        List<Integer> deck = new ArrayList<>(IntStream.range(0, 52).boxed().toList());
        Collections.shuffle(deck);
        byte[] bytes = new byte[deck.size()];
        for (int i = 0; i < deck.size(); i++) {
            bytes[i] = deck.get(i).byteValue();
        }
        System.out.println(Arrays.toString(bytes));
        System.out.println(deck.stream().map(App::toCard).toList());
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println(encoded);
    }

    public static String toCard(int b) {
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
