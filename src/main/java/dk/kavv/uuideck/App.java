package dk.kavv.uuideck;

import dk.kavv.uuideck.decks.DeckGenerator;
import dk.kavv.uuideck.decks.RunningIntegerDeck;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.encoding.EncoderType;
import dk.kavv.uuideck.encoding.SixBitCompressor;
import dk.kavv.uuideck.pipeline.PipelineFactory;
import dk.kavv.uuideck.random.StringSeedGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Random;
import java.util.concurrent.Callable;

import static picocli.CommandLine.ExitCode;
import static picocli.CommandLine.Option;

@Command(name = "uuideck", mixinStandardHelpOptions = true)
public class App implements Callable<Integer> {
    private final DeckGenerator deckGenerator = new RunningIntegerDeck();
    private final SixBitCompressor compressor = new SixBitCompressor();
    private Encoder encoder;
    @Option(names = {"-s", "--seed-string"})
    private String seedString;
    @Option(names = {"-n", "--seed-number"})
    private Long seedNumber;
    @Option(names = {"-d", "--decode"})
    private String encoded;
    // Default values could be here in simple cases, but the logic will get much more complicated, so it's in the PipelineFactory.
    @Option(names = {"-e", "--encoder"}, description = "${COMPLETION-CANDIDATES}")
    private EncoderType encoderType;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        encoder = PipelineFactory.getEncoder(encoderType);
        if (encoded != null) {
            decodeDeck(encoded);
        } else {
            generateDeck();
        }
        return ExitCode.OK;
    }

    public void decodeDeck(String in) {
        byte[] deck = encoder.decode(in);
        deck = compressor.decompress(deck);
        deckGenerator.present(deck);
    }

    public void generateDeck() {
        Random r;
        if (seedString != null) {
            StringSeedGenerator seedGenerator = new StringSeedGenerator();
            r = new Random(seedGenerator.generate(seedString));
        } else if (seedNumber != null) {
            r = new Random(seedNumber);
        } else {
            r = new Random();
        }

        byte[] deck = deckGenerator.generate(r);
        deckGenerator.present(deck);
        byte[] compressed = compressor.compress(deck);
        String encoded = encoder.encode(compressed);
        System.out.println(encoded);
    }
}