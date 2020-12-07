scalaVersion := "2.13.3"
name := "odersky"
organization := "mayton.tutorials"
version := "1.0"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

mainClass in (Compile, run) := Some("mayton.odersky.chapter8.section9.TailRec")