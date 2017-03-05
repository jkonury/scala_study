package ch07

object Procedural2FP extends App {
  def printMultiTable() {
    var i = 1
    while (i <= 10) {
      var j = 1
      while (j <= 10) {
        val prod = (i * j).toString
        var k = prod.length

        while (k < 4) {
          print(" ")
          k += 1
        }
        print(prod)
        j += 1
      }
      println()
      i += 1
    }
  }

  printMultiTable()

  // 하나의 열을 시퀀스로 반환
  def makeRowSeq(row: Int): Seq[String] = {
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }
  }

  // 하나의 열을 문자열로 반환
  def makeRow(row: Int): String = makeRowSeq(row).mkString

  def multiTable(): String = {
    val tableSeq =
      for (row <- 1 to 10)
        yield makeRow(row)


    tableSeq.mkString("\n")
  }

  println()
  println(multiTable())
}
