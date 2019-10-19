name := "pg-watchdog-tables"

version := "0.1"

scalaVersion := "2.12.9"

libraryDependencies += "org.postgresql" % "postgresql" % "42.2.6"

libraryDependencies += "commons-cli" % "commons-cli" % "1.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4"

mainClass in (Compile, run) := Some("mayton.spark.ProbeSpark")




