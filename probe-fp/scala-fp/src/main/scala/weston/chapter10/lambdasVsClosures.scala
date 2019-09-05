package weston.chapter10

object lambdasVsClosures {

  // A closure is a special type of lambda. It’s an anonymous function but it also captures
  //something from its environment when created.

  //Every closure is a lambda but not all lambdas are closures! To make it more confusing, an
  //anonymous class can also be called a closure when in captures some data.

  //Let’s take a look at an example. Given the following interface Condition:

  /////////////////////////////////////////////////////////////////////////

  //  Cheat Sheet
  //  If a lambda has no arguments and references something outside of its scope, it’s also a closure.
  //  If a lambda works only on things passed in as parameters, it’s not a closure.

  //(x) -> x * 2;     // lambda
  // () -> x * 2;      // closure

  //
}
