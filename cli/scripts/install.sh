#!/usr/bin/env bash

# Possible improvements
# 1. Make wrapper executable instead and put in ~/.local/bin
# 2. Put completion in bash-completion dir instead of eager sourcing.
# 3. If none of those, check XDG for rc location.

echo "Copying artifacts"
mkdir -p ~/uuideck
cp -f target/{uuideck.jar,uuideck_completion} ~/uuideck

append_wrapper_function () {
  echo "Adding wrapper function (if needed) to $1"
  grep uuideck --quiet "$1" || cat << 'EOF' >> "$1"
uuideck () {
  java -jar ~/uuideck/uuideck.jar "$@"
}
. ~/uuideck/uuideck_completion
EOF
}

if [ -e ~/.bashrc ]; then
  append_wrapper_function ~/.bashrc
fi

if [ -e ~/.zshrc ]; then
  append_wrapper_function ~/.zshrc
fi
