#!/usr/bin/env bash
mvn clean install -DskipTests

docker run -p 443:443 -t delicious_provider:0.0.1-SNAPSHOT