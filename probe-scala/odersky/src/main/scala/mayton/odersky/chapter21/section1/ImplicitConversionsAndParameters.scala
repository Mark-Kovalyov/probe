package mayton.odersky.chapter21.section1

object ImplicitConversionsAndParameters extends App {



    implicit def intToString(x : Int) = x.toString



}
