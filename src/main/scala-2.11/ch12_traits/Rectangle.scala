package ch12_traits

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
}

object Rectangle extends App {
  val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
  println(rect.left)
  println(rect.right)
  println(rect.width)

}
