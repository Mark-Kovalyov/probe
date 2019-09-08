package geeksforgeeks.org

object Currying {

    // Syntax1:
    //  def function name(argument1) = (argument2) => operation
    // --------------------------------------------------------------------------------------
    // Scala program add two numbers
    // using Currying function

    // transforming the function that
    // takes two(multiple) arguments into
    // a function that takes one(single) argument.
    def add2(a: Int) = (b: Int) => a + b;

    // Syntax2:
    //    def function name(argument1) (argument2) = operation
    // --------------------------------------------------------------------------------------
    // Scala program add two numbers
    // using Currying function
    // Curring function declaration
    def add3(a: Int)(b: Int) = a + b;

    // Main method
    def main(args: Array[String]) {

        if (args.size > 0) {
            println("Param1 = " + args(0) + " param2 = " + args(1))
        }

        println(add2(20)(19));

        println(add3(29)(5));

        // Partially Applied function.
        val sum2 = add2(29);
        println(sum2(5));


    }

}
