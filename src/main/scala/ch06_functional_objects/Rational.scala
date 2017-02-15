package ch06_functional_objects

import scala.language.implicitConversions

class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g

  def this(n: Int) = this(n, 1)

  def + (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def - (that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def - (i: Int): Rational = new Rational(numer - 1 * denom, denom)

  def * (that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational = new Rational(numer * i, denom)

  def / (that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def / (i: Int): Rational = new Rational(numer, denom * i)


  override def toString: String = s"$numer/$denom"

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

}

object Rational {
  def main(args: Array[String]) {
    val x = new Rational(2, 3)
    println(s"x [$x]")

    implicit def intToRational(x: Int): Rational = new Rational(x)

    val r = new Rational(2, 3)
    println(s"2 * r [${2 * r}]")
  }
}