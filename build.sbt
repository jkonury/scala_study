name := "scala_study"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12",
  "org.scalaz" % "scalaz-core_2.12" % "7.2.8",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.scalatest" % "scalatest_2.12" % "3.0.1"
)
