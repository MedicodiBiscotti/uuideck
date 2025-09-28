package dk.kavv.uuideck.decks;

import java.util.List;

public class SetSpecFactory {
    public static SetSpec getSpec(SetType setType, List<String> customSet, Integer customLength) {
        // XOR: Must be either both true or both false. If one but not the other, it fails.
        if (setType == SetType.customSet ^ customSet != null) {
            throw new IllegalArgumentException("Set must be supplied with custom set type");
        }
        if (setType == SetType.customLength ^ customLength != null) {
            throw new IllegalArgumentException("Length must be supplied with custom length type");
        }
        return (setType != null) ? switch (setType) {
            case french -> new FrenchSuitedDeck();
            case german -> new GermanSuitedDeck();
            case swiss -> new SwissSuitedDeck();
            case italian -> new ItalianSuitedDeck();
            case tarot -> new TarotDeck();
            case customSet -> new CustomSet(customSet);
            case customLength -> new CustomLengthSet(customLength);
        } : new FrenchSuitedDeck();
    }
}
