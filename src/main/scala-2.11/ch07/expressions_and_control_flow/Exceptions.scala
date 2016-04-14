package ch07.expressions_and_control_flow

object Exceptions {
  def throws1 = throw new IllegalArgumentException

  def throws2(n: Int) = {
    val half =
      if (n % 2 == 0)
        n / 2
      else
        throw new RuntimeException("n must be even")
    half
  }

  def throws3 = {
    import java.io.FileReader
    import java.io.FileNotFoundException
    import java.io.IOException

    try {
      val f = new FileReader("input.txt")
      println(s"f [$f]")
    } catch {
      case ex: FileNotFoundException =>
        println(s"ex [$ex]")
      case ex: IOException =>
        println(s"ex [$ex]")
    }
  }

  def finally1: Unit = {
    import java.io.FileReader

    val file = new FileReader("input.txt")
    try {

    } finally {
      file.close()
    }
  }

  import java.net.URL
  import java.net.MalformedURLException

  def urlFor(path: String) =
    try {
      new URL(path)
    } catch {
      case e: MalformedURLException =>
        new URL("http://www.scala-lang.ong")
    }

  def f(): Int = try { return 1 } finally { return 2 }
  def g(): Int = try { 1 } finally { return 2 }

  def main(args: Array[String]) {
    try {
      throws1
    } catch {
      case ex =>
        println(s"ex [$ex]")
    }

    try {
      println(s"throws2(2) [${throws2(2)}]")
      throws2(3)
    } catch {
      case ex: RuntimeException =>
        println(s"ex [$ex]")
    }

    throws3
    println(s"urlFor(${"blah"}) [${urlFor("blah")}]")
    println(s"f [${f()}]")
    println(s"g [${g()}]")
  }
}
