#!/usr/bin/env bash

CRDIR="$(cd "`dirname "$0"`"; pwd)"
FWDIR="$(cd "`dirname "$0"`"/..; pwd)"


cd ${FWDIR}/repack/maven

mvn clean install


cd ${FWDIR}/repack/sbt

sbt "clean;publishM2"


cd ${FWDIR}

${FWDIR}/gradlew clean testClasses


cd ${FWDIR}/repack/sbtRef

sbt "clean;publishM2"