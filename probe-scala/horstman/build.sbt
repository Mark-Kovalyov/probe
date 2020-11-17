scalaVersion := "2.13.3"

name := "Tutorials from Horstman"
organization := "mayton"
version := "1.0"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

libraryDependencies += "org.tpolecat" %% "doobie-postgres"  % "0.9.0"
libraryDependencies += "org.tpolecat" %% "doobie-core"      % "0.9.0"

mainClass in (Compile, run) := Some("mayton.technology.ProbeDoobie")