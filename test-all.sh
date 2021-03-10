#!/bin/bash -v

rm "tested-ok.lst"
rm "tested-bad.lst"

cd java

for folder in */ ; do
  if [ -d "$folder" ]; then
    cd "$folder"
    if [[ -f "pom.xml" ]]; then
      mvn clean test
      if [ $? -eq 0 ]
      then
        echo "$folder mvn" >> "../tested-ok.lst"
      else
        echo "$folder mvn returns $?" >> "../tested-bad.lst"
      fi
    fi
    if [[ -f "build.gradle" ]]; then
      gradle clean test
      if [ $? -eq 0 ]
      then
        echo "$folder gradle" >> "../tested-ok.lst"
      else
        echo "$folder gradle returns $?" >> "../tested-bad.lst"
      fi
    fi
    cd ..
  fi
done;

cd ..
