package ch12_traits

object Ex02 extends App {
  val queue = new BasicIntQueue with Incrementing with Filtering
  queue.put(-1)
  queue.put(0)
  queue.put(1)
  println(queue.get())
  println(queue.get())
//  println(queue.get())
}
