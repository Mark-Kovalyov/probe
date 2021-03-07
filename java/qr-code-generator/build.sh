#!/bin/bash

mvn clean package
cp ./target/QRCodeGen.jar qr-code-gen.jar
