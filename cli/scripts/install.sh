#!/usr/bin/env bash

# Possible improvements
# 1. Make wrapper executable instead and put in ~/.local/bin
# 2. Put completion in bash-completion dir instead of eager sourcing.
# 3. If none of those, check XDG for rc location.
# 4. If Linux, install to ~/.local/share.

dir=~/uuideck
data_dir=$dir/data
echo "Copying artifacts"
mkdir -p $dir
cp -f target/{uuideck.jar,uuideck_completion} $dir
mkdir -p $data_dir
cp -f ../data/* $data_dir


append_wrapper () {
  echo "Adding wrapper function/alias (if needed) to $1"
  grep uuideck --quiet "$1" || cat << EOF >> "$1"
alias uuideck='java -jar $dir/uuideck.jar "\$@"'
export UUIDECK_DATA_DIR=$data_dir
. $dir/uuideck_completion
EOF
# Annoyingly, ~ has been expanded to absolute path in $dir.
}

# Another option is rc=$(case $SHELL in *zsh) echo ~/.zshrc;; *bash) echo ~/.bashrc;; esac

if [ -e ~/.bashrc ]; then
  append_wrapper ~/.bashrc
fi

if [ -e ~/.zshrc ]; then
  append_wrapper ~/.zshrc
fi
