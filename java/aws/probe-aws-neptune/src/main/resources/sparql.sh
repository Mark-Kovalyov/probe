#!/bin/bash -v

curl http://your-neptune-endpoint:8182/sparql/status

# Cancel query

curl http://your-neptune-endpoint:8182/sparql/status \
    --data-urlencode "cancelQuery" \
    --data-urlencode "queryId=fb34cd3e-f37c-4d12-9cf2-03bb741bf54f"

# Load data

curl -X POST \
    -H 'Content-Type: application/json' \
    http://your-neptune-endpoint:8182/loader -d '
    {
      "source" : "s3://bucket-name/object-key-name",
      "format" : "format",
      "iamRoleArn" : "arn:aws:iam::account-id:role/role-name",
      "region" : "region",
      "failOnError" : "FALSE"
    }'