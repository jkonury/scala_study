package ch09_control_abstraction

object Demo {
  def containNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0)
        exists = true
    exists
  }

  def containNeg2(nums: List[Int]) = nums.exists(_ < 0)

  def containOdd(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num % 2 == 1)
        exists = true
    exists
  }

  def containOdd2(nums: List[Int]) = nums.exists(_ % 2 == 1)

  def plainOldSum(x: Int, y: Int) = x + y
  def curriedSum(x: Int)(y: Int) = x + y

  def main(args: Array[String]) {
    println("=======================================")
    println(containNeg(Nil))
    println(containNeg(List(1, 2, 3, 4)))
    println(containNeg(List(1, 2, -3, 4)))
    println("=======================================")

    println("=======================================")
    println(containNeg2(Nil))
    println(containNeg2(List(1, 2, 3, 4)))
    println(containNeg2(List(1, 2, -3, 4)))
    println("=======================================")

    println("=======================================")
    println(containOdd(List(1, 2, 3)))
    println(containOdd(List(2, 4, 6)))
    println("=======================================")

    println("=======================================")
    println(containOdd2(List(1, 2, 3)))
    println(containOdd2(List(2, 4, 6)))
    println("=======================================")
  }
}
