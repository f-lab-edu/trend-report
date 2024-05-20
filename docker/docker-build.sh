#!/bin/sh

#Setting Versions
VERSION='1.0.0'

cd ..
./gradlew clean build -x test

ROOT_PATH=$(pwd)
echo "$ROOT_PATH"

echo 'user docker image build... Start'
cd "$ROOT_PATH"/user && docker build -t user:$VERSION .
echo 'user docker image build... Finish'

echo 'external docker image build... Start'
cd "$ROOT_PATH"/external && docker build -t external:$VERSION .
echo 'external docker image build... Finish'
