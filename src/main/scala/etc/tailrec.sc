import scala.annotation.tailrec

//@tailrec => 꼬리재귀 최적화 되지 않아 컴파일 오류
def sum(x: Int, y: Int)(f: Int => Int): Int =
  if (x > y) 0
  else f(x) + sum(x + 1, y)(f)

//sum(1, 10000)(identity)  => StackOverflowError
sum(1, 10)(x => x)
sum(1, 10)(_ * 2)


@tailrec
def sumOfN(acc:Int, x: Int): Int =
  if (x == 0) acc
  else sumOfN(acc + x, x - 1)

sumOfN(0, 10000)