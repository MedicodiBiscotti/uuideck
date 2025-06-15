package dk.kavv.uuideck;

import dk.kavv.uuideck.decks.DeckGenerator;
import dk.kavv.uuideck.decks.RunningIntegerDeck;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.encoding.EncoderFactory;
import dk.kavv.uuideck.encoding.EncoderType;
import dk.kavv.uuideck.encoding.IncompatibleComponentsException;
import dk.kavv.uuideck.errorhandling.ShortBusinessExceptionHandler;
import dk.kavv.uuideck.random.StringSeedGenerator;
import dk.kavv.uuideck.version.PropertyVersionProvider;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;

import static picocli.CommandLine.ExitCode;
import static picocli.CommandLine.Option;

@Command(name = "uuideck", mixinStandardHelpOptions = true, versionProvider = PropertyVersionProvider.class)
public class App implements Callable<Integer> {
    @Spec
    private CommandSpec spec;
    @Option(names = {"-s", "--seed-string"})
    private String seedString;
    @Option(names = {"-n", "--seed-number"})
    private Long seedNumber;
    @Option(names = {"-d", "--decode"})
    private String encoded;

    // Component options
    // Default values could be here in simple cases, but the logic will get much more complicated, so it's in the ComponentsFactory.
    @Option(names = {"-e", "--encoder"}, description = "Options: ${COMPLETION-CANDIDATES}%nDefault: all")
    private EncoderType encoderType;
    @Option(names = {"-c", "--compression"}, negatable = true, description = "Default: true")
    private Optional<Boolean> doCompression;

    private DeckGenerator deckGenerator = new RunningIntegerDeck();
    private Encoder encoder;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App())
                .setExecutionExceptionHandler(new ShortBusinessExceptionHandler())
                .execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            encoder = EncoderFactory.getEncoder(doCompression, encoderType);
        } catch (IncompatibleComponentsException e) {
            throw new ParameterException(spec.commandLine(), String.join(System.lineSeparator(), e.getErrors()));
        }
        if (encoded != null) {
            try {
                decodeDeck(encoded);
            } catch (UnsupportedOperationException e) {
                throw new ParameterException(spec.commandLine(), e.getMessage());
            }
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
        } else if (seedNumber != null) {
            r = new Random(seedNumber);
        } else {
            r = new Random();
        }

        byte[] deck = deckGenerator.generate(r);
        deckGenerator.present(deck);
        String encoded = encoder.encode(deck);
        System.out.println(encoded);
    }
}