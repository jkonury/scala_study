package ch04_class_object

import scala.collection.mutable

class ChecksumAccumulator {
  private var sum = 0

  def add(b: Byte) { sum += b }

  def checksum(): Int = ~(sum & 0xFF) + 1


  /*
  def add(b: Byte): Unit = {
    sum += b
  }

  결과 타입이 Unit 인 경우 부수 효과를 위해 사용한다는 뜻이다.
  Side Effect는 보통 외부의 상태를 변경하거나 I/O를 수행하는 것으로 정의한다.
  예를 들어 add의 경우 sum을 재할당한다.
  이런 메소드를 표현하는 또 다른 방법은 결과 타입을 생략하고 등호(=)도 없애면서
  메소드 본체를 중괄호로 감싸는 것이다. 이렇게 형태를 바꾸면 메소드가 마치 프로시저(procedure) 같아 보인다.
  프로시저란 오직 부수 효과를 얻기 위해서만 사용하는 메소드를 의미한다.

  함수 본문 앞에 있는 등호를 빼먹으면 결과 타입이 Unit으로 바뀐다는 점이다.
   */
}
object ChecksumAccumulator {
  private val cache = mutable.Map[String, Int]()

  def calculate(s: String): Int =
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += s -> cs
      cs
    }
}
