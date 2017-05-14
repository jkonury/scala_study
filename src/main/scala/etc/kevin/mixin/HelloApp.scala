package etc.kevin.mixin

object HelloApp extends App {
  trait A {
    def doSomething(): Unit
  }
  trait B {
    def doSomething2(): Unit = println("B")
  }
  trait C {
    def doSomething3(): Unit = println("C")
  }

  class Something extends A {
    def doSomething(): Unit = println("Something")
  }

  val something = new Something with B with C
  something.doSomething()
  something.doSomething2()
  something.doSomething3()
}
