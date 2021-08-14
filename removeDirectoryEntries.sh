#!/bin/bash

# Rezips all built .jar files, to remove directory entries.
# This reduces the file size by a small amount.
for file in ./build/libs/*.jar
do
  unzip "$file" -d ./build/libs/temp
  rm "$file"
  # TODO replace this with standard zip
  advzip "$file" --shrink-store --pedantic -a ./build/libs/temp/**
  rm -rf ./build/libs/temp/
done
