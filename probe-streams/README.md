# Streams

Angelica Langer https://www.youtube.com/watch?v=oWlWEKNM5Aw

# JDK refs

## Reducers:

See JDK-8 https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

```Optional<T> reduce(BinaryOperator<T> accumulator)```

Performs a reduction on the elements of this stream, using an associative **accumulation function**, and returns an **Optional** describing the reduced value, if any.

```T reduce(T identity, BinaryOperator<T> accumulator)```

Performs a reduction on the elements of this stream, using the **provided identity value** and an associative accumulation function, and returns the reduced value.

```<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)```

Performs a reduction on the elements of this stream, using the **provided identity**, **accumulation** and **combining** functions.

## Collectors:

```<R,A> R collect(Collector<? super T,A,R> collector)```
Performs a **mutable reduction** operation on the elements of this stream using a **Collector**.


```<R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)```
Performs a **mutable reduction** operation on the elements of this stream.

## Streams in Java 8

- equivalent of sequence from functional programming language

## Obtain a stream

- collection 

```myCollection.stream()```

- array

```Arrays.stream(myarr)```

- resulting stream 
  - does not store any elements
  - just a view of underlying stream source
  
## Parallel Stream

- collection 

```myCollection.parallelStream()```

- array

```Arrays.stream(myarr).parallel()```

- performs stream operations in parallel with multiple worker threads from fork-join-common pool

## Primitive Stream Interfaces

- IntStream LongStream DoubleStream
- Performance optimizations form primitives

ex:

```mapToInt()```

## Reduce/Collect

- Both reduce the stream to result

### reduce

- classical reduse known from FP
- performs an immutable reduction (reduction produces new value, but in java types offten mutable, some exceptions ex:String)
  
### collect     
  
- performs a mutable reduction (mutates the result object)
- specially added to the stream API, because Java types are mutable
- need to apply a reduction performed by a mutating method of a mutable type

## Philosophical context

- programming paradigms
  - function => immutable types
  - imperative => mutable
  
- adding lambdas and streams to Java doe's not make it a FP language

- streams are more a reinterpretation of sequences in the existing imperative Java language
  - collect - a mutable reduction
  - intermediate statefull operations
  
## An example with reduce

Calculate sum of all string length

```int sum = myStream.mapToInt(s -> s.length).reduce(0, (l1,l2) -> l1 + l2);```

Reduce operation/accumulator

```(int l1,int l2) -> { return l1 + l2; }```

- immutable
  - provides new result
  
- uses IntStream operation

```int reduce(int identity, IntBinaryOperator acc)```
  - where identity value must be an identity for the accumulator function
  - where accumulator must be ac associative function:

```((a op b) op c) == (a op (b op c))```

- both requirement holds in our example
  - a + 0 == a for all int values a
  - a + b == b + a 
  
Concludes that the int addition is commutative 

## Why these requrements?

- Parallel streams implementation of reduce
  - based on fork-join framework
  
- each task feeds the initial value to the accumulator function
```int sum_1 = streamChunk_1_3.mapToInts(s -> s.length).reduce(0, (l1,l2) -> l1 + l2);```

## Do not use...

- non-identity as initial value
- non-associative as accumulator function

- works with sequental stream, but 
  - no guarantees (javadoc e.t.c)
  
- extremely fragile 
  - adding parallel blows it
  
### Parallel reduce with minus "-" operation

- ignores this requirement

## IntStream / Stream<Integer>

- previous example    
```int sum = myStream.mapToInt(s -> s.length).reduce(0, (l1,l2) -> l1 + l2);```
  - based on IntStreams 
  ```int reduce(int identity, IntBinaryOperator op)```
  
- less performance alternative
```int sum = myStream.map(s -> s.length).reduce(0, (l1,l2) -> l1 + l2);```
  - based on Stream<T>    
  ```T reduce(T identity, IntBinaryOperator op)```

## Problem with this solution

- lot of autoboxing-unboxing because this:
```.reduce(0, (l1,l2) -> l1 + l2)```

- translates to:
```(Integer l1, Integer l2) -> Integer.valueOf(l1.intValue) + l2.intValue))```

- redunant new Integer objects due to Integer cache

## Map-reduce

- Stream<T> providers operation:

```
<U> U reduce(U identity, 
             BiFunction<U, ? super T, U> accumulator,
             BinaryOperator<U>           combiner)
```

- allows to map from T to U
- perform an immediate reduction to U

```
int myStream.reduce( 0,                     // neutral element
                    (l, s) -> l + s.length, // accumulator
                    (s1,s2) -> s1 + s2      // combiner
);
```

- combiner not used for sequental stream
- but null cannot be used as a combiner
  - operations check that function parameters are not null
  - throws NPE otherwise
- advice - always provide correct combiner

## Another example with String

- concatenates String elements

```String s = myStream.reduce("", (s1,s2) -> s1 + s2)```

### _Problem with this solution_

- imperformant
  - s1 + s2 produces new String
  - every time the reduction applied
  
- problem
  - overhead because String - immutable
  - would like to mutate first parameter to represent the result
  
### A solution based on StringBuilder

- Stream<T> provide:

```<R,A> R Collect(Collector<? super T, A, R> collector)```

- a prededined collectors can be found in java.util.Stream.collectors

- solution

```String s = myStream.collect(Collectors.joining())
```  

### Benchmark result:

- collect significally faster thatn reduce
  - sequental 500x faster
  - parallel 150x faster
  
- advantage of mutable type StringBuilder

# Dictionary

https://medium.com/@zaid.naom/exploring-folds-a-powerful-pattern-of-functional-programming-3036974205c8

We have the Initial Value (totalSum, result, totalCount etc.) 
that we are defining at the very beginning. This initial 
value is also called the **Identity Value** or the **Neutral Element**.

