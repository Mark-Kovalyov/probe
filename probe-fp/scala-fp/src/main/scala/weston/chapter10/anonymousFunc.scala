package weston.chapter10

object anonymousFunc {

  def f1 () : Unit = {
    (a: String, b: String) => {
    val comparison = a.compare (b)
    comparison
    }
  }

}
