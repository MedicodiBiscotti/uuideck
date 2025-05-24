# UUIDeck

A deck of 52 cards have `52!` (factorial) different permutations. That's 226 bits of entropy, i.e. a very large
number, `8.06581751709e+67` or `80658175170943878571660636856403766975289505440883277824000000000000` to be exact.

And ~99.999% of those are solvable games of FreeCell.

Ever wanted to generate a UUID that doubles as a deck of cards?

## Install

Sorry for cluttering the user folder, but it's the simplest way to just "put it somewhere".

```shell
mvn clean package
[ -d ~/uuideck ] || mkdir ~/uuideck
cp -f target/{uuideck.jar,uuideck_completion} ~/uuideck
```

The final step appends a wrapper function `~/.bashrc`.
This only needs to be done once (unless the function get updated). If only the actual program changes, omit this final
command:

```shell
cat << 'EOF' >> ~/.bashrc
uuideck() {
  java -jar ~/uuideck/uuideck.jar "$@"
}
. ~/uuideck/uuideck_completion
EOF
```

Copying the artifact could easily be a build step, but appending the wrapper functions would be more difficult using a
Maven plugin.

Alternatively, you could have a wrapper script in a `scripts` directory and add it to `PATH`.
