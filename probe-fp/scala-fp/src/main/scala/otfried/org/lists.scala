package otfried.org

object lists {

  def main(args : Array[String]) : Unit = {

    val list_1 = 2 :: 3 :: 5 :: Nil

    println(list_1)

    val list_2 = (7 :: 11 :: 13 :: Nil) ::: (17 :: 19 :: Nil)

    println(list_2)

    val list_3 = List("a", "bc", "de f")

    println(list_3)

    val list_4 = list_1 ++ list_2

    println(list_4)

    // ( (2, 3, 5), 7, 11, 13, 17, 19)
    val list_5 = list_1 +: list_2

    println(list_5)

    // (2, 3, 5, (7, 11, 13, 17, 19))
    val list_6 = list_1 :+ list_2

    println(list_6)



  }

}
