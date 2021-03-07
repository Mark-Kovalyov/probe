package docs.scala.lang

object lowerTypeBounds {

  // While upper type bounds limit a type to a subtype of
  // another type, lower type bounds declare a type to be a
  // supertype of another type. The term B >: A expresses
  // that the type parameter B or the abstract type B refer to
  // a supertype of type A. In most cases, A will be the
  // type parameter of the class and B will be the type parameter of a method.
  trait Node[+B] {
    def prepend[U >: B](elem: U): Node[U]
  }

  case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
    def head: B = h
    def tail: Node[B] = t
  }

  case class Nil[+B]() extends Node[B] {
    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
  }

  trait Bird

  case class AfricanSwallow() extends Bird

  case class EuropeanSwallow() extends Bird



  def main(args : Array[String]) : Unit = {
    val africanSwallowList= ListNode[AfricanSwallow](AfricanSwallow(), Nil())
    val birdList: Node[Bird] = africanSwallowList
    birdList.prepend(new EuropeanSwallow)
  }

}
