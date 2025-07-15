package dk.kavv.uuideck.encoding;

import dk.kavv.uuideck.compression.NoOpCompressor;
import dk.kavv.uuideck.utils.FactoradicUtils;

import java.math.BigInteger;

public class DecimalEncoder extends AbstractFactoradicEncoder {
    private final Base64Encoder base64Encoder = new Base64Encoder(new NoOpCompressor());

    @Override
    public String encode(byte[] bytes) {
        byte[] lehmer = getFactoradic(bytes);
        BigInteger decimal = getDecimal(lehmer);
        return base64Encoder.encode(decimal.toByteArray()) + " " + decimal;
    }

    @Override
    public byte[] decode(String s) {
        BigInteger decimal = new BigInteger(base64Encoder.decode(s));
        byte[] lehmer = getFactoradic(decimal);
        return FactoradicUtils.decodeLehmer(lehmer);
    }
}
