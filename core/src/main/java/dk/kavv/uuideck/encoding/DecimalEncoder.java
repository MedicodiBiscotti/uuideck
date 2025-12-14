package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.decks.SetSpec;
import dk.kavv.uuideck.utils.FactoradicUtils;
import dk.kavv.uuideck.utils.NumberUtils;

import java.math.BigInteger;

public class DecimalEncoder extends AbstractFactoradicEncoder {
    private final Base64Encoder base64Encoder = new Base64Encoder(new NoOpCompressor());
    private final SetSpec spec;

    public DecimalEncoder(SetSpec spec) {
        this.spec = spec;
    }

    @Override
    public String encode(byte[] bytes) {
        byte[] lehmer = getFactoradic(bytes);
        BigInteger decimal = getDecimal(lehmer);
        String decimalString = NumberUtils.toText(decimal);
        String percentageString = NumberUtils.bigPercentage(decimal, FactoradicUtils.factorial(spec.getLength()), 4);
        // This is getting ridiculous.
        return base64Encoder.encode(decimal.toByteArray()) + " " + decimal + " " + percentageString + " " + decimalString;
    }

    @Override
    public byte[] decode(String s) {
        BigInteger decimal = new BigInteger(base64Encoder.decode(s));
        byte[] lehmer = getFactoradic(decimal, spec);
        return FactoradicUtils.decodeLehmer(lehmer);
    }
}
