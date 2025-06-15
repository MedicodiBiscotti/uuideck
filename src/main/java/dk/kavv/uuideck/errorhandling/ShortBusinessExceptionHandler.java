package dk.kavv.uuideck.errorhandling;

import picocli.CommandLine;

import static picocli.CommandLine.IExecutionExceptionHandler;
import static picocli.CommandLine.ParseResult;

public class ShortBusinessExceptionHandler implements IExecutionExceptionHandler {
    @Override
    public int handleExecutionException(Exception ex, CommandLine cmd, ParseResult parseResult) throws Exception {
        if (ex instanceof ShortException) {
            cmd.getErr().println(cmd.getColorScheme().errorText(ex.getMessage()));
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
}
