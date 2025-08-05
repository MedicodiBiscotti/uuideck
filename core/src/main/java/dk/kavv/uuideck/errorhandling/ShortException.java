package dk.kavv.uuideck.errorhandling;

/**
 * Marker interface for not printing the stacktrace when the exception is propagated up to error handler.
 * Not great that this violates separation of concerns, but it was an easy, scalable solution.
 */
public interface ShortException {
}
