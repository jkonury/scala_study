package ch03_next_steps

import scala.io.Source

object FileLineRead extends App {

  if (args.length > 0) {
    for (line <- Source.fromFile(args(0)).getLines())
      println(line.length + " " + line)
  } else
    Console.err.println("Please enter filename")

  val lines = Source.fromFile(args(0)).getLines().toList

  def widthOfLength(s: String) = s.length.toString.length

  var maxWidth = 0

  for (line <- lines) {
    maxWidth = maxWidth.max(widthOfLength(line))
  }

  val longestLine = lines.reduceLeft(
    (a, b) => if (a.length > b.length) a else b
  )

  for (line <- lines) {
    val numSpaces = maxWidth - widthOfLength(line)
    val padding = " " * numSpaces
    println(s"$padding ${line.length} | $line")
  }
}
