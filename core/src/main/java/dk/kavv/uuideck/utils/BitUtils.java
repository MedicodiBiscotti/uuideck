package dk.kavv.uuideck.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BitUtils {
    public static int bitLengthOfNumber(int value) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(value);
    }
}
