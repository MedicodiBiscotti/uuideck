#!/usr/bin/env bash

echo "Copying artifacts"
[ -d ~/uuideck ] || mkdir ~/uuideck
cp -f target/{uuideck.jar,uuideck_completion} ~/uuideck

echo "Adding wrapper function (if needed)"
grep uuideck --quiet ~/.bashrc || cat << 'EOF' >> ~/.bashrc
uuideck () {
  java -jar ~/uuideck/uuideck.jar "$@"
}
. ~/uuideck/uuideck_completion
EOF
