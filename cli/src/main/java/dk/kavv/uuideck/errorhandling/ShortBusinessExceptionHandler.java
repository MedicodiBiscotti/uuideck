package dk.kavv.uuideck.errorhandling;

import dk.kavv.uuideck.App;
import picocli.CommandLine;

import java.nio.file.FileSystemException;

import static picocli.CommandLine.IExecutionExceptionHandler;
import static picocli.CommandLine.ParseResult;

public class ShortBusinessExceptionHandler implements IExecutionExceptionHandler {
    @Override
    public int handleExecutionException(Exception ex, CommandLine cmd, ParseResult parseResult) throws Exception {
        // Subject to change in favor of logging.
        int verbosity = ((App) cmd.getCommandSpec().userObject()).getVerbosity().length;
        if (verbosity > 0) {
            throw ex;
        }
        if (ex instanceof ShortException) {
            printErr(cmd, ex.getMessage());
        } else if (ex instanceof FileSystemException) {
            printErr(cmd, "Could not access file: %s".formatted(((FileSystemException) ex).getFile()));
        } else {
            // Doesn't print red message
            // cmd.getErr().println(cmd.getColorScheme().stackTraceText(ex));
            // Default implementation just throws.
            throw ex;
        }
        return cmd.getExitCodeExceptionMapper() != null
                ? cmd.getExitCodeExceptionMapper().getExitCode(ex)
                : cmd.getCommandSpec().exitCodeOnExecutionException();
    }

    private void printErr(CommandLine cmd, String text) {
        cmd.getErr().println(cmd.getColorScheme().errorText(text));
    }
}
