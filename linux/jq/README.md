# JQ

https://stedolan.github.io/jq/manual/

## Examples:

### Parse AWS responce with JQ and regexp expressions

```bash
$ aws cloudformation list-stacks --output json | \
     jq '.StackSummaries[] | select(.StackName|test("acc-lambda-v01"))'
```