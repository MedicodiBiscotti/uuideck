package dk.kavv.uuideck;

import dk.kavv.uuideck.decks.DeckGenerator;
import dk.kavv.uuideck.decks.RunningIntegerDeck;
import dk.kavv.uuideck.encoding.EightBitBase64Encoder;
import dk.kavv.uuideck.encoding.Encoder;

import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        DeckGenerator deckGenerator = new RunningIntegerDeck();
        Encoder encoder = new EightBitBase64Encoder();

        byte[] deck = deckGenerator.generate(new Random());
        System.out.println(Arrays.toString(deck));
        deckGenerator.present(deck);

        String encoded = encoder.encode(deck);
        System.out.println(encoded);
    }
}