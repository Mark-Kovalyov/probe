package mayton.scala

import org.apache.camel.main.Main
import org.apache.camel.scala.dsl.builder.RouteBuilderSupport

/**
 * A Main to run Camel with MyRouteBuilder
 */
object MyRouteMain extends RouteBuilderSupport {

  def main(args: Array[String]) {
    val main = new Main()
    // create the CamelContext
    val context = main.getOrCreateCamelContext()
    // add our route using the created CamelContext
    main.addRouteBuilder(new MyRouteBuilder(context))
    // must use run to start the main application
    main.run()
  }
}

