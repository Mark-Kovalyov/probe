call mvn clean package
xcopy .\target\QRCodeGen.jar .
java -jar QRCodeGen.jar