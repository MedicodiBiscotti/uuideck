package dk.kavv.uuideck.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvUtils {
    public static List<String> getElements(Reader reader) throws IOException {
        CSVParser csv = CSVFormat.DEFAULT.parse(reader);
        return csv.stream()
                .flatMap(CSVRecord::stream)
                // stripping and filtering may be undesired but also smooths out user input.
                .map(String::strip)
                // isBlank also exist, but I think this might be more efficient if we strip anyway.
                // can use either Predicate.not or lambda.
                .filter(Predicate.not(String::isEmpty))
                .toList();
        // If quoted values, DO NOT have leading spaces before the quotes. It parses CSV before stripping,
        // sees first char is space (not quote), and will interpret quotes as part of the value.
    }
}
