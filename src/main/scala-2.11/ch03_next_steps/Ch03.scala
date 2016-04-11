package ch03_next_steps

import scala.collection.immutable.HashMap

object Ch03 {
  def main(args: Array[String]) {
    val greetStrings = new Array[String](3)
    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "World!"

    for (i <- 0 to 2 ) {
      println(greetStrings(i))
    }

    val greetStrings2 = new Array[String](3)
    greetStrings2.update(0, "Hello")
    greetStrings2.update(1, ", ")
    greetStrings2.update(2, "World")

    for (i <- 0 to 2 ) {
      println(greetStrings2.apply(i))
    }

    val numName = Array("zero", "one", "two")
    val numName2 = Array.apply("zero", "one", "two")
    println(numName sameElements numName2)
    println(numName.length)
    println(numName2.length)

    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = oneTwo ::: threeFour

    println(s"$oneTwo, $threeFour")
    println(s"$oneTwoThreeFour")

    val twoThree = List(2, 3)
    val oneTwoThree = 1 :: twoThree
    println(oneTwoThree)

    val oneTwoThree2 = 1 :: 2 :: 3 :: Nil
    println(oneTwoThree == oneTwoThree2)

    val x = new HashMap[Int, String]
    val y: Map[Int, String] = new HashMap
    println(x == y)
  }
}
