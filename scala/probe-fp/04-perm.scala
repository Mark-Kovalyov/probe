/*
C(4,6)=15
123456
 1,23456
  12,3456
   123,456
    1234,56 => "1234"
    1235,6  => "1235"
    1236,() => "1236"
23456
 2,3456
  23,
3456

 */

def init[A](a : List[A]) : List[A] = a.slice(0, a.length - 1)

def permx[A](k : Int, list : List[A]) : LazyList[List[A]] = {
  if (list.length > k) {
    list #:: permx(k, list.tail)
  } else {
    list #:: LazyList.empty
  }
}

def permx(k: Int, n : Int) : LazyList[List[Int]] = {
  permx(k, List.range(1, n + 1))
}

// scala> permx(4,6).force
// val res48: scala.collection.immutable.LazyList[List[Int]] = LazyList(List(1, 2, 3, 4, 5, 6), List(2, 3, 4, 5, 6), List(3, 4, 5, 6))