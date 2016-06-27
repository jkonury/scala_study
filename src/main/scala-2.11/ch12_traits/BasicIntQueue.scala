package ch12_traits

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue{
  private val buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)
  override def put(x: Int): Unit = buf += x
}

object BasicIntQueue extends App {
  val queue = new BasicIntQueue
  queue.put(10)
  queue.put(20)
  println(queue.get())
  println(queue.get())

  class MyQueue extends BasicIntQueue with Doubling

  val myQueue = new MyQueue
  myQueue.put(100)
  println(myQueue.get())

  val myQueue2 = new BasicIntQueue with Doubling
  myQueue2.put(200)
  println(myQueue2.get())
}
