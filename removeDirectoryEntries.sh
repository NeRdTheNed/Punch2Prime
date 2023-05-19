#!/bin/bash

# Rezips all built .jar files, to remove directory entries.
# This reduces the file size by a small amount.
for file in ./build/libs/*.jar
do
  unzip "$file" -d ./build/libs/temp
  rm "$file"
  find ./build/libs/temp/ -iname '*.json' -type f -print0 | while IFS= read -r -d $'\0' jsonFile
  do
    if [[ "$OSTYPE" == "darwin"* ]]; then
      if ! command -v gsed &> /dev/null
      then
        echo "Please install GNU sed as gsed"
      else
        jq -c . < "$jsonFile" | gsed -z '$ s/\n$//' > "$jsonFile-tempOut"
      fi
    else
      jq -c . < "$jsonFile" | sed -z '$ s/\n$//' > "$jsonFile-tempOut"
    fi
    mv "$jsonFile-tempOut" "$jsonFile"
  done
  # TODO replace this with standard zip
  advzip "$file" --shrink-store --pedantic -a ./build/libs/temp/**
  rm -rf ./build/libs/temp/
done
