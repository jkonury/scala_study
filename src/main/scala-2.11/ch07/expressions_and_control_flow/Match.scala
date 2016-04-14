package ch07.expressions_and_control_flow

object Match {
  def match1(args: Array[String]): Unit = {
    val firstArg = if (args.length > 0) args(0) else ""

    firstArg match {
      case "slat" => println("pepper")
      case "chips" => println("salsa")
      case "eggs" => println("bacon")
      case _ => println("huh")
    }
  }

  def match2(args: Array[String]): Unit = {
    val firstArg = if (!args.isEmpty) args(0) else ""

    val friend =
      firstArg match {
        case "slat" => "pepper"
        case "chips" => "salsa"
        case "eggs" => "bacon"
        case _ => "huh"
      }
    println(friend)
  }

  def main(args: Array[String]) {
    match1(Array())
    match1(Array("foo"))
    match1(Array("eggs"))

    match2(Array("chips"))
  }
}
