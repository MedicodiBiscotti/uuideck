package dk.kavv.uuideck;

import dk.kavv.uuideck.compression.CompressorType;
import dk.kavv.uuideck.decks.*;
import dk.kavv.uuideck.encoding.Encoder;
import dk.kavv.uuideck.encoding.EncoderFactory;
import dk.kavv.uuideck.encoding.EncoderType;
import dk.kavv.uuideck.encoding.IncompatibleComponentsException;
import dk.kavv.uuideck.errorhandling.ShortBusinessExceptionHandler;
import dk.kavv.uuideck.random.StringSeedGenerator;
import dk.kavv.uuideck.utils.CsvUtils;
import dk.kavv.uuideck.version.PropertyVersionProvider;
import lombok.Getter;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
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
    // Newlines can also be done with %n instead of arrays.
    @Option(names = {"-e", "--encoder"}, description = {
            "Options: ${COMPLETION-CANDIDATES}",
            "Default: all"})
    private EncoderType encoderType;
    @Option(names = {"-c", "--compression"}, description = {
            "Options: ${COMPLETION-CANDIDATES}",
            "Default: bit"})
    private CompressorType compressorType;
    @Option(names = {"-T", "--set-type"}, description = {
            "Options: ${COMPLETION-CANDIDATES}",
            "Default: ${DEFAULT-VALUE}"})
    private SetType setType = SetType.french;

    @Option(names = {"-N", "custom-length"})
    private Integer customLength;
    @Option(names = {"-F", "custom-set"})
    private Path customSetPath;
    private List<String> customSet;

    @Getter
    @Option(names = {"-v", "--verbose"}, description = {
            "Specify multiple -v options to increase verbosity.",
            "For example, `-v -v -v` or `-vvv`"})
    private boolean[] verbosity = new boolean[0];

    private DeckGenerator deckGenerator = new RunningIntegerDeck();
    private Encoder encoder;
    private SetSpec setSpec;

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new App())
                .setExecutionExceptionHandler(new ShortBusinessExceptionHandler())
                .execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            if (customSetPath != null && customSetPath.toString().equals("-")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                if (!reader.ready()) {
                    throw new ParameterException(spec.commandLine(), "No data in standard in");
                }
                customSet = CsvUtils.getElements(reader);
            }
            setSpec = SetSpecFactory.getSpec(setType, customSet, customLength);
            encoder = EncoderFactory.getEncoder(compressorType, encoderType, setSpec);
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
        System.out.println(setSpec.present(deck));
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

        byte[] deck = deckGenerator.generate(setSpec, r);
        System.out.println(setSpec.present(deck));
        String encoded = encoder.encode(deck);
        System.out.println(encoded);
    }
}