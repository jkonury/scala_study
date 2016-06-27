package ch12_traits

trait Doubling extends IntQueue {
  abstract override def put(x: Int) = super.put(2 * x)
}

/*
extends IntQueue : IntQueue 를 상속한 클래스에만 믹스인 할 수 있음
*/