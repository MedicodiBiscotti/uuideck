package dk.kavv.uuideck.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberUtils {

    public static final String[] ONES = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    public static final String[] TEN_TO_NINETEEN = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    public static final String HUNDRED = "hundred";
    public static final String[] THOUSANDS = {"thousand",
            "million",
            "billion",
            "trillion",
            "quadrillion",
            "quintillion",
            "sextillion",
            "septillion",
            "octillion",
            "nonillion",
            "decillion",
            "undecillion",
            "duodecillion",
            "tredecillion",
            "quattuordecillion",
            "quindecillion",
            "sexdecillion",
            "septendecillion",
            "octodecillion",
            "novemdecillion",
            "vigintillion",
            "unvigintillion",
            "duovigintillion",
            "trevigintillion",
            "quattuorvigintillion",
            "quinvigintillion",
            "sexvigintillion",
            "septenvigintillion",
            "octovigintillion",
            "novemvigintillion",
            "trigintillion",
            "untrigintillion",
            "duotrigintillion",
            "tretrigintillion",
            "quattuortrigintillion",
            "quintrigintillion",
            "sextrigintillion",
            "septentrigintillion",
            "octotrigintillion",
            "novemtrigintillion",
            "quadragintillion",
            "unquadragintillion",
            "duoquadragintillion",
            "trequadragintillion",
            "quattuorquadragintillion",
            "quinquadragintillion",
            "sexquadragintillion",
            "septenquadragintillion",
            "octoquadragintillion",
            "novemquadragintillion",
            "quinquagintillion",
            "unquinquagintillion",
            "duoquinquagintillion",
            "trequinquagintillion",
            "quattuorquinquagintillion",
            "quinquinquagintillion",
            "sexquinquagintillion",
            "septenquinquagintillion",
            "octoquinquagintillion",
            "novemquinquagintillion",
            "sexagintillion",
            "unsexagintillion",
            "duosexagintillion",
            "tresexagintillion",
            "quattuorsexagintillion",
            "quinsexagintillion",
            "sexsexagintillion",
            "septensexagintillion",
            "octosexagintillion",
            "novemsexagintillion",
            "septuagintillion",
            "unseptuagintillion",
            "duoseptuagintillion",
            "treseptuagintillion",
            "quattuorseptuagintillion",
            "quinseptuagintillion",
            "sexseptuagintillion",
            "septenseptuagintillion",
            "octoseptuagintillion",
            "novemseptuagintillion",
            "octogintillion",
            "unoctogintillion",
            "duooctogintillion",
            "treoctogintillion",
            "quattuoroctogintillion",
            "quinoctogintillion",
            "sexoctogintillion",
            "septenoctogintillion",
            "octooctogintillion",
            "novemoctogintillion",
            "nonagintillion",
            "unnonagintillion",
            "duononagintillion",
            "trenonagintillion",
            "quattuornonagintillion",
            "quinnonagintillion",
            "sexnonagintillion",
            "septennonagintillion",
            "octononagintillion",
            "novemnonagintillion",
            "centillion",
            "uncentillion",
            "duocentillion",
    };

    /**
     * Spells out a number in text.
     *
     * @param number Number to spell out.
     * @return Textual representation of the number.
     */
    /*
    https://en.wikipedia.org/wiki/Names_of_large_numbers
    https://simple.wikipedia.org/wiki/Names_of_large_numbers
    Can use the Conway–Guy system to create names for arbitrarily large numbers beyond 10^309 of my static list. More difficult to implement.
    10^309 should be enough for 171! (factorial).
    You can verify results with https://www.calculatorsoup.com/calculators/conversions/numberstowords.php.
     */
    public static String toText(Number number) {
        if (number.intValue() == 0) return "zero";
        String s = number.toString();
        // I'd rather pad once than check for length every time.
        String padded = padToGroupOfThree(number);
        return ONES[number.intValue()];
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
