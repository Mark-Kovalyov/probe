call mvn clean package

java -jar target\aws-s3-length-report-1.0.jar ^
        --endpoint   s3.eu-west-1.amazonaws.com ^
        --bucket     bucket ^
        --region     eu-west-1 ^
        --prefix     00 
