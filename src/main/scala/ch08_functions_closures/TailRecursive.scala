package ch08_functions_closures

import scala.annotation.tailrec

object TailRecursive extends App {
  def boom(x: Int): Int =
    if (x == 0)
      throw new Exception("boom!")
    else
      boom(x - 1) + 1

//  boom(3)

  @tailrec
  def bang(x: Int): Int =
    if (x == 0)
      throw new Exception("bang!")
    else
      bang(x - 1)

  bang(5)

  // 스칼라는 동일한 함수를 직접 재취 호출하는 경우에는 최적화를 수행
  // 아래 예제는 꼬리 재귀 최적화가 적용되지 않는다.

  def isEven(x: Int): Boolean =
    if (x == 0) true else isOdd(x - 1)

  def isOdd(x: Int): Boolean =
    if (x == 0) false else isEven(x - 1)

  val funValue = nestedFun _
  def nestedFun(x: Int): Unit = {
    if (x != 0) {
      println(x)
      funValue(x - 1)
    }
  }
}
