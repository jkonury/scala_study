package ch08_functions_closures

import scala.io.Source

object LongLine {
  def processFile(filename: String, width: Int) {
    def processLine(line: String) {
      if (line.length > width)
        println(s"filename : $line")
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)
  }
}
