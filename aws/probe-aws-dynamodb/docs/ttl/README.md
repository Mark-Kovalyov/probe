# TTL

```
aws dynamodb update-time-to-live --table-name sessions \
  --time-to-live-specification "Enabled=true, AttributeName=ttl"
```

```
aws dynamodb describe-time-to-live --table-name sessions
{
    "TimeToLiveDescription": {
        "AttributeName": "ttl",
        "TimeToLiveStatus": "ENABLED"
    }
} 
```

```
EXP=`date -d '+5 days' +%s`
aws dynamodb put-item --table-name "sessions" --item '{"id": {"N": "1"}, "ttl": {"N": "'$EXP'"}}'
```