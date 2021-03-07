package weston

import scala.collection.mutable

trait Sortable[A <: Ordered[A]] extends Iterable[A] {
  def sort() : Seq[A] = {
    this.toList.sorted
  }
}

class Item(val value : Double) {

}

class ShoppingBasket {
  var items : mutable.Set[Item] = mutable.HashSet()

  def add(item : Item) : Unit = {
    items += item
  }

  def total() : Double = {
    var sum : Double = 0.0
    items.foreach(i => sum += i.value)
    sum
  }
}

class Customer(val name : String,val addr : String) {

  private final val basket: ShoppingBasket = new ShoppingBasket

  def add(item: Item) {
    basket.add(item)
  }

  def total: Double = {
    basket.total()
  }
}

class DiscountedCustomer(name : String, addr : String) extends Customer(name, addr) {

}

/*
class Customers extends Sortable[Customer] {

  private val customers = mutable.Set[Customer]()

  def add(customer: Customer) : Boolean = {
    customers.add(customer)
  }

  def iterator: Iterator[Customer] = {
    customers.iterator
  }
}
*/

object Customers {

  def main(args: Array[String]) {
/*    val customers = new Customers()
    customers.add(new Customer("Fred Jones", "8 Tuna Lane,"))
    customers.add(new Customer("Velma Dinkley", "316 Circle Drive"))
    customers.add(new Customer("Daphne Blake", "101 Easy St"))
    customers.add(new DiscountedCustomer("Norville Rogers", "1 Lane"))
    println(customers.sort)*/
  }

}
