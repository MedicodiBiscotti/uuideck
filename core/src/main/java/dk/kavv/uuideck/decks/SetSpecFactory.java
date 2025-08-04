package dk.kavv.uuideck.decks;

public class SetSpecFactory {
    public static SetSpec getSpec(SetType setType) {
        return (setType != null) ? switch (setType) {
            case french -> new FrenchSuitedDeck();
        } : new FrenchSuitedDeck();
    }
}
