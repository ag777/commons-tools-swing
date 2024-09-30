@echo off
mvn install:install-file "-DgroupId=github.ag777" "-DartifactId=common-tools-swing" "-Dversion=1.0-SNAPSHOT" "-Dpackaging=jar" "-Dfile=./target/common-tools-swing-1.0-SNAPSHOT.jar"