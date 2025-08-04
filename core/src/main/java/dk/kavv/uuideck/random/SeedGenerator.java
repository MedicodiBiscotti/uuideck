package dk.kavv.uuideck.random;

public interface SeedGenerator<T> {
    long generate(T in);
}
