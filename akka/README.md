# Akka

JPoint 2014 - 

* 300 bytes/per actor

## State

* state
* behave
* mailbox
* childs
* supervisor strategy

* No delivery guarantee, at-most-once, keep order

* Receiving : keep order
* hierarchy
* Parent - supervisor
* ActorRef
* "Let it crash"


References
```
Local

akka://system/user/a/b

Remote

akka.tcp://system@server.yandex/user/a/b
```

Create:
```
* ActorRefProvider.actorOf
* Lookup:
    ActorRefProvider.actorSelection
```

Partitioner
```
class Paritioner(parSt : ActorRef) extends Actor {

   def receive = {
     
   }

}
```


## Dispatchers

* PinnedDispatcher
* BalancingDispatcher
* CallingThreadDispatcher

## Mailboxes

* UnboundedMailbox
* Bounded
* UnboundedPriority
* BoundedPriority

```
akka.actor.deployment {
 partitioner {
   router = smallest-mailbox-pool
   nr-of-inst = 4
 }
}
```

## Routers

Pool/Group

* RoundRobin
* Random
* SmallestMailbox
* Broadcast
* ScatterGatherFirstCompleted

## Remote actors

```
akka {

 actor {
   provider = "alla.remote.RemoteActorRefProvider"
 }

 remote {
   enabled-transports = ["akka.remote.netty.tcp"]
   netty.tcp {
     ....
   }
 }
}
```
