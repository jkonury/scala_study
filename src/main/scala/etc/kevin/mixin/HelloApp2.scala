package etc.kevin.mixin

/**
  * @author Jang Ji Hong
  * @since 2017.04.06
  */
object HelloApp2 extends App {
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
    def doSomething(): Unit = println("Something")
  }

  val something = new Something with B with C
  something.doSomething()
}
