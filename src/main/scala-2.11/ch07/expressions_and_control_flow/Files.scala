package ch07.expressions_and_control_flow

import java.io.File

import scala.io.Source

object Files {
  val filesHere = new File(".").listFiles

  def printFiles() =
    for (file <- filesHere)
      println(file)

  def printScalaFiles(): Unit = {
    val filesHere = new File(".").listFiles

    for (file <- filesHere if file.getName.endsWith(".scala"))
      println(file)
  }

  def printScalaFiles2(): Unit = {
    for (file <- filesHere)
      if (file.getName.endsWith(".scala"))
        println(file)
  }

  def printScalaFiles3(): Unit = {
    for (
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".scala")
    ) println(file)
  }

  def fileLines(file: File) =
    Source.fromFile(file).getLines().toList

  def grepParens(pattern: String): Unit = {
    def grep() =
      for (
        file <- filesHere
        if file.getName.endsWith(".scala");
        line <- fileLines(file)
        if line.trim.matches(pattern)
      ) println(s"file : ${line.trim}")
    grep()
  }

  def grepGcd(): Unit = {
    def grep(pattern: String) = grepParens(pattern)
    grep(".*gcd.*")
  }

  def grepGcd2(): Unit = {
    def grep(pattern: String) =
      for {
        file <- filesHere
        if file.getName.endsWith(".scala")
        line <- fileLines(file)
        trimmed = line.trim
        if trimmed.matches(pattern)
      } println(s"file : $trimmed")
    grep(".*gcd.*")
  }

  def scalaFiles =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file

  val forLineLengths =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(".*for.*")
    } yield trimmed.length

  def main(args: Array[String]) {
    printFiles()
    printScalaFiles()
    printScalaFiles2()
    printScalaFiles3()
    grepParens(".*asdf.*")
    grepGcd()
    grepGcd2()

    println(s"scalaFiles.toList [${scalaFiles.toList}]")
    println(s"forLineLengths.toList [${forLineLengths.toList}]")
  }
}
