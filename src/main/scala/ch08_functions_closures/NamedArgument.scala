package ch08_functions_closures

object NamedArgument extends App {
  def spend(distant: Float, time: Float): Float = distant / time

  println(spend(100, 10))
  println(spend(distant = 100, time = 10))
  println(spend(time = 10, distant = 100))
}
