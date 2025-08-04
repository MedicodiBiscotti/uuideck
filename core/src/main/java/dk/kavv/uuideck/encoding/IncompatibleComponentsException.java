package dk.kavv.uuideck.encoding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class IncompatibleComponentsException extends RuntimeException {
    private final List<String> errors;
}
