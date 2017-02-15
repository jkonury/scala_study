package ch17_collections

import scala.collection.immutable.{TreeMap, TreeSet}
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Collections extends App {
  // List
  val colors = List("red", "blue", "green")
  println(colors.head)
  println(colors.tail)
  println(colors)

  // Array
  val fiveInts = new Array[Int](5)
  println(fiveInts mkString " ")

  val fiveToOne = Array(5, 4, 3, 2, 1)
  println(fiveToOne mkString " ")

  // ListBuffer
  val listBuffer = new ListBuffer[Int]
  listBuffer += 1
  listBuffer += 2
  println(listBuffer)
  println(listBuffer.length)

  println(3 +=: listBuffer)

  // ArrayBuffer
  val arrayBuffer = new ArrayBuffer[Int]()
  arrayBuffer += 12
  arrayBuffer += 15
  println(arrayBuffer)
  println(arrayBuffer.length)

  // Set
  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ .,!]+")
  println(wordsArray mkString " ")

  val words = mutable.Set.empty[String]
  for (word <- wordsArray)
    words += word.toLowerCase
  println(words)

  // Map
  val map = mutable.Map.empty[String, Int]
  map("hello") = 1
  map("there") = 2
  println(map)

  println(countWords("See Spot run! Run, Spot. RUn!"))

  def countWords(text: String) = {
    val counts = mutable.Map.empty[String, Int]
    for (ranWord <- text.split("[ ,!.]+")) {
      val word = ranWord.toLowerCase
      val oldCount =
        if (counts.contains(word)) counts(word)
        else 0
      counts += (word -> (oldCount + 1))
    }
    counts
  }

  // Ordered Set&Map
  val ts = TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)
  println(ts)
  val cs = TreeSet("f", "u", "n")
  println(cs)

  var tm = TreeMap(3 -> 'x', 1 -> 'x', 4 -> 'x')
  println(tm)
  tm += (2 -> 'x')
  println(tm)
}
