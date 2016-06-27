package ch12_traits

object Ex01 {
  class Animal

  class Frog extends Animal with Philosophical {
    override def toString = "green"
  }

  def main(args: Array[String]) {
    println(s"new Frog [${new Frog}]")
  }
}
