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