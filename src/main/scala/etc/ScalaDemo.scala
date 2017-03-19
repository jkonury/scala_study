package etc

import scalaz.{-\/, \/, \/-}
import scalaz.Scalaz._

object ScalaDemo extends App {
  def doWhatever(x: Int): String \/ Int =
    if (x < 0) -\/("x must b a non-negative number")
    else \/- (x * 2)

  doWhatever(-1) match {
    case -\/(error) => println(s"error: $error")
    case \/-(x) => println(s"result: $x")
  }
  doWhatever(1) match {
    case -\/(error) => println(s"error: $error")
    case \/-(x) => println(s"result: $x")
  }

  println(List(1, 2, 3).flatMap(x => x :: List(111)))
  println(List(1, 2, 3) >>= (x => x :: List(111)))
}
