# AWS Step-Functions

* Task - a signle unit of work
* Choice - add brancing logic
* Parallel - fork and join the data across tasks
* Wail - delay for specific time
* Fail - stop and execution and marks it as a failure
* Succeed - Stops and execution successfully
* Pass - passes its input to its output



Commands
```
Available Commands
******************

* create-activity
* create-state-machine
* delete-activity
* delete-state-machine
* describe-activity
* delete-state-machine
* describe-activity
* describe-execution
* describe-state-machine
* describe-state-machine-for-execution
* get-activity-task
* get-execution-history
* help
* list-activities
* list-executions
* list-state-machines
* list-tags-for-resource
* send-task-failure
* send-task-heartbeat
* send-task-success
* start-execution
* stop-execution
* tag-resource
* untag-resource
* update-state-machine
```

```
aws 
  create-state-machine
   --name <value>
   --definition <value>
   --role-arn <value>
   [--type <value>]
   [--logging-configuration <value>]
   [--tags <value>]
   [--cli-input-json <value>]
   [--generate-cli-skeleton <value>]
```

## Definition (Hello world)

```
{
  "Comment": "Hello world",
  "StartAt": "Beginning",
  "States": {
    ....
  }
}

```

## Wait for a Callback with the Task Token

See https://docs.aws.amazon.com/step-functions/latest/dg/connect-to-resource.html#connect-wait-token

```
"Send message to SQS": {
  "Type": "Task",
  "Resource": "arn:aws:states:::sqs:sendMessage.waitForTaskToken",
  "Parameters": {
    "QueueUrl": "https://sqs.us-east-2.amazonaws.com/123456789012/myQueue",
    "MessageBody": {
        "Message": "Hello from Step Functions!",
        "TaskToken.$": "$$.Task.Token"
     }
  },
  "Next": "NEXT_STATE"
}
```

## Exception handling

```
"Catch": [ {
  "ErrorEquals": [ "Lambda.Unknown", "States.Timeout", "java.lang.RuntimeException","Lambda.ServiceException", "Lambda.AWSLambdaException", "Lambda.SdkClientException","Lambda.TooManyRequestsException"],
  "ResultPath": "$.errorInformation",
  "Next": "HandleExceptionBlock"
} ]
```

## Store into DynamoDb with retry

```
"StoreHistory" : {
  "Type":"Task",
  "Resource": "arn:aws:states:::dynamodb:putItem",
  "Parameters": {
    "TableName":"History",
    "Item": {
       "TransactionId" : { "S.$": "$.TransactionId"}
    }
  },
  "Retry" : [
    {
     "ErrorEquals":[States.ALL],
     "IntervalSeconds": 1,
       "MaxAttempts" : 3
     }
  ],
  "Next": "........."
```


## Sequence

```
"HelloWorld" : {
  Type : Task,
  ...
  Retry : [
     ErrorEquals:[States.TaskFailed],
     IntervalSeconds:1,
     MaxAttenpts:2,
     BackoffRate:2.0
  }
```

## Catch Exceptions

```
  HelloWorld : {
    Type : Task ,
    ...
    Catch: [
      {
         ErrorEquals:[AllYourBaseAreBelongToUs]
         Next:CustomErrorFallBack
      },
      {
         ErrorEquals:[States.TaskFailed]
         Next:ReservedTypeFallback
      }
```


## Pricing

### Free Tier

4000 state transitions per month

### State transitions, Per 1000

$0.025 in most regions