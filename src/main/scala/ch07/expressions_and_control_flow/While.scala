package ch07.expressions_and_control_flow

object While {
  def main(args: Array[String]) {
    def whileExpression(args: String*) = {
      var i = 0
      var foundIt = false

      while (i < args.length && !foundIt) {
        if (!args(i).startsWith("-")) {
          if (args(i) endsWith ".scala")
            foundIt = true
        }
        i += 1
      }
      println(s"foundIt = $foundIt")
      println(s"i = $i")
    }

    whileExpression("Test.scala", "Sample.java")

    def searchFrom(i: Int): Int =
      if (i >= args.length) -1
      else if (args(i) startsWith "-") searchFrom(i + 1)
      else if (args(i) endsWith ".scala") i
      else searchFrom(i + 1)

    println(s"searchFrom(0) = ${searchFrom(0)}")
  }
}
