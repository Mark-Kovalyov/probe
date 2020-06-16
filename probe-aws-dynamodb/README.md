# DynamoDB

Docker
```
docker run -p 8000:8000 amazon/dynamodb-local



```

API 

https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/dynamodbv2/AmazonDynamoDB.html

CLI

https://docs.aws.amazon.com/cli/latest/reference/dynamodb/index.html

## Semantics

* tables
* items
* attributes

Each attribute has a key and a value.

An item can ave any number of attributes up to 64k

* String UTF-8
* Number: 38 digit

## Pefromance

* Items are indexed by PK
* Single hash keys and composite keys.

Hash key + Range key

* PutItem, UpdateItem, DeleteItem can take optional conditions for op.
* UpdateItem performs atomic inc

One API call, multiple items

* BatchGet returns multiple items by pk
* BatchWrite performs up to 25 put/del ops
* Thoughput measured by IO not API calls

Query vs Scan

* Query for composite key queries
* Scan for FTS, exprts
* Both support pages and limits
* Max response is 1Mb

Query patterns

* Retrieve all items by hash key
* Range key conditions ==, <, >, begins with, between
* Counts. Top and bottom N. Pages

Relationship with range

* No cross-table joins
* Use composite keys to model relationships

Store a pointer to objects in S3




Samples:

List tables
```
aws dynamodb list-tables
```

Describe
```
aws dynamodb describe-table --table-name MusicCollection
```

Count items:
```
aws dynamodb scan --table-name <TABLE_NAME> --select "COUNT"

{
    "Count": 7,
    "ScannedCount": 7,
    "ConsumedCapacity": null
}
```

create-table
```
aws dynamodb create-table \
    --table-name MusicCollection \
    --attribute-definitions AttributeName=Artist,AttributeType=S AttributeName=SongTitle,AttributeType=S \
    --key-schema AttributeName=Artist,KeyType=HASH AttributeName=SongTitle,KeyType=RANGE \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
```

create-global-table
```
aws dynamodb create-global-table \
    --global-table-name MusicCollection \
    --replication-group RegionName=us-east-2 RegionName=us-east-1 \
    --region us-east-2
```

Batch run
```
aws dynamodb batch-write-item --request-items "file://$file.json" 
```