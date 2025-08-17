package dk.kavv.uuideck.decks;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CustomSet implements SetSpec {
    private final List<String> elements;

    @Override
    public int getLength() {
        return elements.size();
    }

    @Override
    public String present(byte[] bytes) {
        return IntStream.range(0, bytes.length)
                .mapToObj(i -> elementName(bytes[i]))
                .collect(Collectors.joining(", "));
    }

    @Override
    public String elementName(byte value) {
        return elements.get(value);
    }
}
