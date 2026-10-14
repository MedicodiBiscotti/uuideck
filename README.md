# UUIDeck

A deck of 52 cards have `52!` (factorial) different permutations. That's 226 bits of entropy, i.e. a very large
number, `8.06581751709e+67` or `80658175170943878571660636856403766975289505440883277824000000000000` to be exact.

And ~99.999% of those are solvable games of FreeCell.

Ever wanted to generate a UUID that doubles as a deck of cards?

```shell
$ uuideck -n0
9H, QC, KC, JD, 3C, 5H, KH, 10S, JC, 6S, 9D, 6C, 10H, JS, 6D, 5D, AH, 3H, 4H, 8C, QD, 8H, 2S, KS, 8S, AC, KD, 2H, 7C, 2C, 7S, 9S, 7D, 4S, AS, 5S, 2D, 5C, QH, 8D, 4C, JH, AD, 7H, 9C, 10D, 10C, QS, 3S, 4D, 3D, 6H
Base64 (uncompressed):  LxgZJA8rMwkXBSISMAofHicpKhQlLgEMBw0mKBMOBgggAwAEGxEyIRAxGi0VIxYLAh0cLA==
Base64 (compressed):    L5aRzzonVyFKsPJ5Z6pSpRswR2Oik2Mg4AAQWySHUKy11WgtQsex
ASCII:                  W@AL7S[1?-J:X2GFOQR<MV)4/5NP;6.0H+(,C9ZI8YBU=K>3*EDT
Decimal (encoded):      ArtTQ4JKEJt8d2qiVxkL8IGjl4_Mo4h1Zh2exHo=
Decimal:                73647544578574144870055376110498167205349048242740685691975840482426
Decimal percentage:     91.3082%
Decimal name:           seventy-three unvigintillion six hundred forty-seven vigintillion five hundred forty-four novemdecillion five hundred seventy-eight octodecillion five hundred seventy-four septendecillion one hundred forty-four sexdecillion eight hundred seventy quindecillion fifty-five quattuordecillion three hundred seventy-six tredecillion one hundred ten duodecillion four hundred ninety-eight undecillion one hundred sixty-seven decillion two hundred five nonillion three hundred forty-nine octillion forty-eight septillion two hundred forty-two sextillion seven hundred forty quintillion six hundred eighty-five quadrillion six hundred ninety-one trillion nine hundred seventy-five billion eight hundred forty million four hundred eighty-two thousand four hundred twenty-six
```

## Planned features

- [x] Basic running integer deck
  generation ([Fisher-Yates shuffle](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle))
- [x] Base64 encoding
- [x] Bit compression
    - [x] 6-bit compression
    - [x] Variable bit compression
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
        - [x] Name of number
        - [x] Percentage out of maximum permutation
    - [ ] [Lehmer code](https://en.wikipedia.org/wiki/Lehmer_code)
- [ ] Hexadecimal colour codes (8, 16, and 24 bit)
- [ ] Bar code/QR code encoding
- [x] Different deck specifications, e.g. different size, suits, ranks, custom sets
    - [x] Custom set as CSV from:
        - [x] Standard in
        - [x] File
        - [x] Library directory
- [ ] JSON format
- [ ] File output
- [ ] Web app API
- [ ] Web app UI
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
