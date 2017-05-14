package etc.kevin.mixin

import etc.kevin.logging.{DummyLogging, SimpleLogging, SimpleSlf4JLogging}

object HelloApp3 extends App {
  trait A {
    def doSomething(): Unit
  }
  trait B extends A {
    override abstract def doSomething(): Unit = {
      println("B")
      super.doSomething()
    }
  }
  trait C extends A {
    override abstract def doSomething(): Unit = {
      println("C")
      super.doSomething()
    }
  }

  class Something extends A {
    this: SimpleLogging[_] =>

    override def doSomething(): Unit = {
      info("doSomething")
      println("Something")
    }
  }

  val something = new Something with B with C with SimpleSlf4JLogging
//  val something = new Something with B with C with DummyLogging
  something.doSomething()
}
