package dk.kavv.uuideck.decks;

public interface SetSpec {
    int getLength();

    String present(byte[] bytes);

    String elementName(byte value);
}
