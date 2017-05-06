package ch09_control_abstraction

object ByNameParameter extends App {
  def byNameAssert(predicate: => Boolean): Unit =
    if (!predicate) throw new AssertionError

  byNameAssert(5 > 3)
  byNameAssert(3 > 5)
}
