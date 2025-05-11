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
        String encoded = Base64.getEncoder().encodeToString(bytes);
        System.out.println(encoded);
    }
}
