#!/usr/bin/env bash

CRDIR="$(cd "`dirname "$0"`"; pwd)"
FWDIR="$(cd "`dirname "$0"`"/..; pwd)"

cd ${FWDIR}/repack/maven

mvn clean install

cd ${FWDIR}

${FWDIR}/gradlew clean testClasses
