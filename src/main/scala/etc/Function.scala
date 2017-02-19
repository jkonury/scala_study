package etc

object Function extends App {
  def f1: (String) => String = (s: String) => s" ($s) "
  def f2: (String) => String = (s: String) => s" {$s} "
  def f3: (String) => String = (s: String) => s" [$s] "

  println(f3(f2(f1("test"))))

  val comb1 = f1 andThen f2 andThen f3
  println(comb1("test"))

  val comb2 = f1 compose f2 compose f3
  println(comb2("test"))

  def fun(x: Int, y: Int): Int = x + y
  def fun2(x: Int)(y: Int): Int = x + y
  def func = (x: Int) => (y: Int) => x + y

  println(fun(1, 2))
  println(fun2(1)(2))
  println(func(1)(2))

  // 부분 적용함수
  def myFunc(x: Int): Int = fun(x, 2)
  println(myFunc(1))

  // 커리 함수
//  def curry: (Int) => Int = fun2(2) _
  def curry = fun2(2) _
  println(curry(1))

  def curry2(x: Int) = func(2)
  println(curry2(2))
}
