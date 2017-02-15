package ch15_pattern_matching

object PatternMatch {
  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(name: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  def checkbinary(expr: Expr): Unit = {
    expr match {
      case BinOp(op, left, right) =>
        println(s"$expr is a binary operation")
      case _ =>
    }
  }

  def checkbinary2(expr: Expr): Unit = {
    expr match {
      case BinOp(_, _, _) =>
        println(s"$expr is a binary operation")
      case _ =>
    }
  }

  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  def deepmatch(expr: Expr) = {
    expr match {
      case BinOp("+", e, Number(0)) => println("a deep match")
      case _ =>
    }
  }

  def startsWithZero(expr: Any) = {
    expr match {
      case 0 => "zero"
      case somethingElse => ""
    }
  }

  def startsWithZero1(expr: List[Int]) = {
    expr match {
      case List(0, _, _) => println("found it")
      case _ =>
    }
  }

  def startsWithZero2(expr: List[Int]) = {
    expr match {
      case List(0, _*) => println("found it")
      case _ =>
    }
  }

  object OtherDescribe {
    def describe(e: Expr): String = (e: @unchecked) match {
      case Number(_) => "a number"
      case Var(_) => "a variable"
    }
  }

  def matchUnOp(expr: Expr) = {
    expr match {
      case UnOp("abs", e @ UnOp("abs", _)) => e
      case _ =>
    }
  }

  def main(args: Array[String]) {
    val varExpr = Var("var")
    val zeroExpr = Number(0)
    val binopExpr = BinOp("=", varExpr, varExpr)
    val unopExpr = UnOp("abs", Number(3))

    checkbinary(varExpr)
    checkbinary(binopExpr)

    checkbinary2(varExpr)
    checkbinary2(binopExpr)

    println(s"describe(Number(4)) [${describe(Number(4))}]")
    println(s"describe(varExpr) [${describe(varExpr)}]")

    println(s"OtherDescribe.describe(Number(4)) [${OtherDescribe.describe(Number(4))}]")
    println(s"OtherDescribe.describe(varExpr) [${OtherDescribe.describe(varExpr)}]")

    println(s"startsWithZero(0) [${startsWithZero(0)}]")
    println(s"""startsWithZero("0") [${startsWithZero("0")}]""")

    deepmatch(zeroExpr)

    println(s"matchUnOp(unopExpr) [${matchUnOp(unopExpr)}]")
    println(s"""matchUnOp(UnOp("abs", unopExpr) [${matchUnOp(UnOp("abs", unopExpr))}]""")
  }
}
