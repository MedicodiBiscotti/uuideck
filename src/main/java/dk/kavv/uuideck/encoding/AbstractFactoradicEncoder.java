package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.decks.SetSpec;
import dk.kavv.uuideck.utils.FactoradicUtils;

import java.math.BigInteger;

/**
 * Caches results of factoradic calculations to reuse and not have to redo them.
 */
public abstract class AbstractFactoradicEncoder implements Encoder {
    private static byte[] factoradic;
    private static BigInteger decimal;

    protected static byte[] getFactoradic(byte[] deck) {
        if (factoradic == null) {
            factoradic = FactoradicUtils.encodeLehmer(deck);
        }
        return factoradic;
    }

    protected static byte[] getFactoradic(BigInteger decimal, SetSpec spec) {
        if (factoradic == null) {
            factoradic = FactoradicUtils.decimalToFactoradic(decimal, spec.getLength());
        }
        return factoradic;
    }

    protected static BigInteger getDecimal(byte[] lehmer) {
        if (decimal == null) {
            decimal = FactoradicUtils.factoradicToDecimal(lehmer);
        }
        return decimal;
    }
}
