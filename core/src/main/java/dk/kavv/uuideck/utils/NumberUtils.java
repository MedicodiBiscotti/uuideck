package dk.kavv.uuideck.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberUtils {
    public static String toText(Number number) {
        String s = number.toString();
        int len = s.length();
        // I'd rather pad once than check for length every time.
        return padToGroupOfThree(number);
    }

    /**
     * Pads a number with 0's to make full groupings of 3, as in thousands, millions, etc.
     *
     * @param number Number to pad.
     * @return String left-padded with zeros.
     */
    public static String padToGroupOfThree(Number number) {
        String s = number.toString();
        int remainder = s.length() % 3;
        if (remainder == 0) return s;
        return "0".repeat(3 - remainder) + s;
    }
}
