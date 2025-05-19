package dk.kavv.uuideck;

import dk.kavv.uuideck.decks.DeckGenerator;
import dk.kavv.uuideck.decks.RunningIntegerDeck;
import dk.kavv.uuideck.encoding.EightBitBase64Encoder;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.random.StringSeedGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

import static picocli.CommandLine.ExitCode;
import static picocli.CommandLine.Option;

@Command(name = "uuideck", mixinStandardHelpOptions = true)
public class App implements Callable<Integer> {
    private final DeckGenerator deckGenerator = new RunningIntegerDeck();
    private final Encoder encoder = new EightBitBase64Encoder();
    @Option(names = {"-s", "--seed-string"})
    private String seedString;
    @Option(names = {"-d", "--decode"})
    private String encoded;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        if (encoded != null) {
            decodeDeck(encoded);
        } else {
            generateDeck();
        }
        return ExitCode.OK;
    }

    public void decodeDeck(String in) {
        byte[] deck = encoder.decode(in);
        deckGenerator.present(deck);
    }

    public void generateDeck() {
        Random r;
        if (seedString != null) {
            StringSeedGenerator seedGenerator = new StringSeedGenerator();
            r = new Random(seedGenerator.generate(seedString));
        } else {
            r = new Random();
        }

        byte[] deck = deckGenerator.generate(r);
        System.out.println(Arrays.toString(deck));
        deckGenerator.present(deck);

        String encoded = encoder.encode(deck);
        System.out.println(encoded);

    }
}