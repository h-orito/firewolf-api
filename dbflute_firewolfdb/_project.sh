#!/bin/bash

export ANT_OPTS=-Xmx2048m

export DBFLUTE_HOME=../mydbflute/dbflute-1.2.9

export MY_PROPERTIES_PATH=build.properties

if [ `uname` = "Darwin" ]; then
  export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
fi
