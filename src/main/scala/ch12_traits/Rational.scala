package ch12_traits

class Rational(n: Int, d: Int) extends Ordered[Rational]{
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

//  def < (that: Rational) = this.numer * that.denom > that.numer * this.denom
//  def > (that: Rational) = that < this
//  def <= (that: Rational) = (this < that) || this == that
//  def >= (that: Rational) = (this > that) || this == that

  override def toString: String = s"$numer/$denom"

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def compare(that: Rational) =
    (this.numer * that.denom) - (that.numer * this.denom)
}

object Rational extends App{
  val half = new Rational(1, 2)
  val third = new Rational(1, 3)
  println(half < third)
  println(half.>(third))
  println(half <= third)
  println(half.>=(third))
}