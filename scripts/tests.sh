#!/bin/bash
# test script for .travis.yml
 
set -e
COMMIT_TAG=$(git tag --contains "${TRAVIS_COMMIT}")
if [[ $COMMIT_TAG =~ release.* ]]; then 
git clone https://github.com/SykoraErik/BachelorThesis.git testRepository
cd testRepository
mvn -Dbase.url=http://staging-new.slepemapy.cz/ clean verify;
fi