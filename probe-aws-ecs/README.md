# ECS

## Fargate

Task definition
```
TaskDefinition:
  Type: AWS::ECS::TaskDefinition  
  Properties:
    Family: !Ref 'ServiceName'
    Cpu: !Ref 'ContainerCpu'
    NetworkMode: awsvpc # Enables ENI creation & attachment to Task
    Memory: !Ref 'ContainerMemory'
    RequiresCompatibilities:



Run task
```
aws ecs run-task ...
    --task-definition chat-application:1
    --network-configuration
         "awsvpcConfiguration = { subnets=[subnet1-id, subnet2-id], securityGroups=[sg-id] }"

```