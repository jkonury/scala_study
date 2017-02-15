package ch08_functions_closures

object RepeatParameter {
  def echo(args: String*) =
    for (arg <- args) println(arg)

  def main(args: Array[String]) {
    echo("one")
    echo("hello", "world")

    val arr = Array("java", "scala", "python")
//    echo(arr) // error: type mismatch

//     배열을 repeat parameter로 전달하기 위해서는 콜론(:)에 _* 기호를 추가해야 함
    echo(arr: _*)

  }
}
