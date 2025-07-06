# UUIDeck

A deck of 52 cards have `52!` (factorial) different permutations. That's 226 bits of entropy, i.e. a very large
number, `8.06581751709e+67` or `80658175170943878571660636856403766975289505440883277824000000000000` to be exact.

And ~99.999% of those are solvable games of FreeCell.

Ever wanted to generate a UUID that doubles as a deck of cards?

## Planned features

- [x] Basic running integer deck
  generation ([Fisher-Yates shuffle](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle))
- [x] Base64 encoding
- [x] 6-bit compression
- [x] ASCII encoding
- [x] Seed random generation
    - [x] Number (64-bit)
    - [x] String (first 8 bytes)
- [x] Multiple encoders at once
- [ ] Verbosity for detailed output
    - [x] Print stack-trace for errors
    - [ ] General logging
- [ ] Raw encoding showing underlying raw data
- [ ] [Factoradic](https://en.wikipedia.org/wiki/Factorial_number_system) encoding
    - [x] Permutation index
    - [ ] [Lehmer code](https://en.wikipedia.org/wiki/Lehmer_code)
- [ ] JSON format
- [ ] File output
- [ ] Different deck specifications, e.g different size, suits, ranks
- [ ] ~~"Suit-signed" 6-bit deck generation (first 2 bits denote the suit)~~

## Install

Sorry for cluttering the user folder, but it's the simplest way to just "put it somewhere".

```shell
mvn clean install
```

Or grab the artifacts from the [GitHub releases](https://github.com/MedicodiBiscotti/uuideck/releases/latest)
and follow the instructions of the install script manually to put them in the right place.

The installation happens as part of the Maven build process. It goes like this:

1. Build and package the fat jar.
2. Generate shell completion script.
3. Make a target folder if it doesn't already exist.
4. Copy the artifacts (jar and completion).
5. Append wrapper function to `~/.bashrc` and source completion.

The full script is in `scripts/install.sh`. Here are the parts explained.

This command generates the completion script. It's done as part of the `package` step in Maven as suggested by the
documentation.

```shell
java -cp target/uuideck.jar picocli.AutoComplete --force dk.kavv.uuideck.App
```

Copying is done with this:

```shell
[ -d ~/uuideck ] || mkdir ~/uuideck
cp -f target/{uuideck.jar,uuideck_completion} ~/uuideck
```

The final step appends a wrapper function to `~/.bashrc`.
This only needs to be done once (unless the function get updated). It'll only execute if the file doesn't already
contain `uuideck`.

```shell
grep uuideck --quiet ~/.bashrc || cat << 'EOF' >> ~/.bashrc
uuideck() {
  java -jar ~/uuideck/uuideck.jar "$@"
}
. ~/uuideck/uuideck_completion
EOF
```
