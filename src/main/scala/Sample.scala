/**
  * @author Jang Ji Hong
  * @since 2017.02.15
  */
object Sample {
  implicit class ToAge(val n: Int) extends AnyVal {
    def years(x: old.type): Age = Age(n)
    def year(x: old.type): Age = years(x)
  }

  case object old
  case class Age(n: Int) {
    override def toString: String = s"$n ${if (n > 1) "years" else "year"} old"
  }

  def main(args: Array[String]) {
    println(Age(10))
    println(10 years old)

  }
}
