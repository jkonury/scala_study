package ch04_class_object

import ChecksumAccumulator.calculate

object FailWinterSpringSummer extends App {
  for (session <- List("fall", "winter", "spring"))
    println(s"$session : ${calculate(session)}")
}
