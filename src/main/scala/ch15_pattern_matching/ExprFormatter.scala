package ch15_pattern_matching



class ExprFormatter {
  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(name: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  private val opGroups =
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )

  private val precedence = {
    val assocs = for {
      i <- opGroups.indices
      op <- opGroups(i)
    } yield op -> i
    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

//  private def format(e: Expr, enclPrec: Int): Element =
}
