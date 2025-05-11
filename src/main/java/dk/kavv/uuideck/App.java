package dk.kavv.uuideck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        List<Integer> deck = new ArrayList<>(IntStream.range(0, 52).boxed().toList());
        Collections.shuffle(deck);
        System.out.println(deck);
    }
}
