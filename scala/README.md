# Scala3

https://www.youtube.com/watch?v=blRnZHQfe0g

## Valueable spaces!

```scala3
trait A:
  def f : Int

object O:
  def f = x
```

## End-constructions
```scala3
for 
  x <- xs
  y <- ys
do
  println(x + y)
end for
```

## Toplevel definitions
```scala3
package math

val E = 2.7

def twice(x : Double) = x * x
```

## Export(OOP)
```scala3
class Derived(x: Base) extends Base:
  export x.*
```
## Trait parameters
```scala3
trait Hello(val name: String):
  def msg = s"Hello $name"
```

## Implicit (Extenstions) 
```scala3
extensions(x: Long) def bytes: Array[byte]=Array((x>>24).toByte,...)
```

## Enum
```
enum Color:
  case Red,Green,Blue
```
instead of
```

```

## Dotty

Dependend Object Type

## SBT archetypes

(A template for Scala 3 projects)
```bash 
sbt new scala/scala3.g8  
```

## Scala2 - Scala3

|Feature|Scala-2|Scala-3|
|-------|-------|-------|
|Speed  | Slow  |   ?
|Inter-version|.scala->.class|.scala->.tasty->.class|