name := "probe-distinct"

version := "0.1"

scalaVersion := "2.12.11"

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) => "GeoIpOrc.jar" }

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.30"

libraryDependencies += "com.amazon.deequ" % "deequ" % "1.0.2"

mainClass in (Compile, packageBin) := Some("mayton.spark.GeoIpOrc")