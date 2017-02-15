package ch12_traits

object Ex03 extends App {
  class Animal {
    def test(s: String) = println(s)
  }
  trait Furry extends Animal {
    override def test(s: String) = super.test(s"$s Furry")
  }
  trait HasLegs extends Animal {
    override def test(s: String) = super.test(s"$s HasLegs")
  }
  trait FourLegged extends HasLegs {
    override def test(s: String) = super.test(s"$s FourLegged")
  }
  class Cat extends Animal with Furry with FourLegged {
    override def test(s: String = "") = super.test("Cat")
  }

  val cat = new Cat
  cat.test()
}
