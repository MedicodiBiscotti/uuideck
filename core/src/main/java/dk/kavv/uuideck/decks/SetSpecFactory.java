package dk.kavv.uuideck.decks;

import java.util.List;

public class SetSpecFactory {
    public static SetSpec getSpec(SetType setType, List<String> customSet, Integer customLength) {
        return (setType != null) ? switch (setType) {
            case french -> new FrenchSuitedDeck();
            case customSet -> {
                if (customSet == null) {
                    throw new IllegalArgumentException("Set must be supplied for custom set");
                }
                yield new CustomSet(customSet);
            }
            case customLength -> {
                if (customLength == null) {
                    throw new IllegalArgumentException("Length must be supplied for custom length");
                }
                yield new CustomLengthSet(customLength);
            }
        } : new FrenchSuitedDeck();
    }
}
