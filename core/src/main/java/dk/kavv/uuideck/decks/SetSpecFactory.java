package dk.kavv.uuideck.decks;

public class SetSpecFactory {
    public static SetSpec getSpec(SetType setType, Integer customLength) {
        return (setType != null) ? switch (setType) {
            case french -> new FrenchSuitedDeck();
            case customLength -> {
                if (customLength == null) {
                    throw new IllegalArgumentException("Length must be supplied for custom length");
                }
                yield new CustomLengthSet(customLength);
            }
        } : new FrenchSuitedDeck();
    }
}
