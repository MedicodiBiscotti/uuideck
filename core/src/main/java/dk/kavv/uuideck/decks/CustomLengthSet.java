package dk.kavv.uuideck.decks;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CustomLengthSet implements SetSpec {
    private final int length;

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String present(byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> elementName(bytes[i]))
                .collect(Collectors.joining(", "));
    }

    @Override
    public String elementName(byte value) {
        return String.valueOf(value);
    }
}
