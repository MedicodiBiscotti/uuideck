package dk.kavv.uuideck.decks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RunningIntegerDeck implements DeckGenerator {
    @Override
    public byte[] generate(SetSpec spec, Random r) {
        int length = spec.getLength();
        List<Integer> deck = new ArrayList<>(IntStream.range(0, length).boxed().toList());
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


}
