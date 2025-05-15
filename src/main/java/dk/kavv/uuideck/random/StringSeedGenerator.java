package dk.kavv.uuideck.random;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class StringSeedGenerator implements SeedGenerator<String> {
    @Override
    public long generate(String in) {
        /*
         Will discard when overflowing a long.
         Could also String concat binary and parse long.
         BitSet.toLongArray() is little-endian (LE). Least significant byte first.
         Concat could be whatever I want, but the most intuitive is big-endian (BE),
         so be mindful that the results are different.
        */
        if (in.isEmpty()) {
            return 0L;
        }
        byte[] bytes = in.getBytes(StandardCharsets.UTF_8);
        BitSet bs = BitSet.valueOf(bytes);
        return bs.toLongArray()[0];
    }
}
