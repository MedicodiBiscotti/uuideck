package dk.kavv.uuideck;

import dk.kavv.uuideck.decks.DeckGenerator;
import dk.kavv.uuideck.decks.RunningIntegerDeck;
import dk.kavv.uuideck.encoding.EightBitBase64Encoder;
import dk.kavv.uuideck.encoding.Encoder;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

@Command(name = "uuideck", mixinStandardHelpOptions = true)
public class App implements Callable<Integer> {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        DeckGenerator deckGenerator = new RunningIntegerDeck();
        Encoder encoder = new EightBitBase64Encoder();

        byte[] deck = deckGenerator.generate(new Random());
        System.out.println(Arrays.toString(deck));
        deckGenerator.present(deck);

        String encoded = encoder.encode(deck);
        System.out.println(encoded);

        return CommandLine.ExitCode.OK;
    }
}