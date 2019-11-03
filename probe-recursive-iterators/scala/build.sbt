name := "scala"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
       "junit" % "junit" % "4.12" % Test,
       "org.scalatest" %% "scalatest" % "3.2.0-M1" % Test,
       "org.scalactic" %% "scalactic" % "3.0.8"
)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")
