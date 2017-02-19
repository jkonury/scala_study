val alphabet = 'a' to 'z'
alphabet.map(_.toUpper)

(1 to 10).filter(_ % 2 == 0)

val vowels = Vector('a', 'e', 'i', 'o')
alphabet.filterNot(vowels.contains(_))

val ns = List(1, 2, 3, 4)
val cs = List('a', 'b', 'c')
ns zip cs

// takeWhile : 조건에 부합할 때 까지 요소를 가져옴.
// 특정 요소에 부합하지 않는 경우 뒤에 요소는 무시
(1 to 10).takeWhile(_ < 5).toList

val (even, odd) = (1 to 10).partition(_ % 2 == 0)
even
odd

val list = (1 to 10).toList

list.takeWhile(_ % 3 != 0)
list.span(_ % 3 != 0)
list.partition(_ % 3 != 0)

list.find(_ % 5 == 0)
list.find(_ % 11 == 0)

val ss = alphabet.map(_.toString)
ss.foldLeft(List.empty[String])(_ :+ _)
ss.foldRight(List.empty[String])(_ +: _)

list.foldLeft(10)(_ + _)
list.scanLeft(10)(_ + _)

List(List(1, List(123)), List(123))
List(List(1, List(123)), List(123)).flatten


val nns = List(List(1, 2), List(3, 4))
nns.flatMap(x => x.map(_ * 2))
nns.flatMap(_.map(_ * 2))