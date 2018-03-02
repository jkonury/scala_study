## Scala Study

### 15장 케이스 클래스와 패턴매치

#### 패턴의 종류

##### 와일드카드 패턴

```scala
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
```

##### 상수 패턴

상수 패턴은 자신과 똑같은 값과 매치된다. 어떤 종류의 리터럴이든 상수로 사용할 수 있다.
예를 들어 5, true, "hello"는 모두 상수 패턴이다. 또한 val이나 싱글톤 객체도 상수로 사용할 수 있다.  
예를 들어 싱글톤 객체인 Nil은 오직 빈 리스트에만 매치시킬 수 있는 패턴이다. 

```scala
  def describe(x: Any): String = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

scala> describe(5)
res6: String = five

scala> describe(true)
res7: String = truth

scala> describe("hello")
res8: String = hi!

scala> describe(Nil)
res9: String = the empty list

scala> describe(List(1,2,3))
res10: String = something else
```

##### 변수 패턴

상수 패턴이 기호로 이뤄진 이름일 수도 있다. Nil을 사용하는 패턴이 그런 경우이다.  
다음 예는 상수 E와 Pi를 패턴 매치한다.

```scala
scala> import math.{E, Pi}
import math.{E, Pi}

scala> E match {
         case Pi => "strange math? Pi = " + Pi
         case _ => "OK"
       }
res11: String = OK
```

예상대로 E는 Pi와 매치되지 않는다.  
스칼라 컴파일러는 어떻게 Pi가 셀렉터와 매치시킬 변수가 아니라, scala.math로부터 임포트한 상수인지 알 수있을까?  
스칼라는 이런 모호성을 없애기 위해 간단한 문법 규칙을 사용한다. 즉, 소문자로 시작하는 간단한 이름은 패턴 변수로 취급하고 다른 모든 참조는 상수로 간주한다. 

```scala
scala> val pi = math.Pi
pi: Double = 3.141592653589793

scala> E match {
         case pi => "strange math? Pi = " + pi
       }
res12: String = strange math? Pi = 2.718281828459045
```

위의 경우 모든 경우를 처리할 수 있는 디폴트 케이스를 추가하려 해도 컴파일러가 허락하지 않는다.  
왜냐하면 pi가 변수 패턴이라서 모든 입력과 매치할 수 있기 때문이다. 그래서 두 번째 와일드카드 케이스에는 결코 도달하지 않는다. 

```scala
scala> E match {
         case pi => "strange math? Pi = " + pi
         case _ => "OK"  
       }
<console>:11: warning: patterns after a variable pattern cannot match (SLS 8.1.1)
                case pi => "strange math? Pi = " + pi
                     ^
<console>:12: warning: unreachable code due to variable pattern 'pi' on line 11
                case _ => "OK"
                          ^
<console>:12: warning: unreachable code
                case _ => "OK"
                          ^
res13: String = strange math? Pi = 2.718281828459045
```

##### 생성자 패턴

생성자는 패턴 매치가 실제로 커다란 위력을 발휘하는 부분이다. 생성자 패턴은 BinOp("+", e, Number(0)) 과 같은 형태이다. 여기서는 이름(BinOp) 다음에 괄호로 둘러싼 여러 패턴인 "+", e, Number(0) 이 왔다. 이름이 케이스 클래스를 가리킨다면 이 패턴의 의미는 어떤 값이 해당 케이스 클래스에 속하는지 검사한 다음 그 객체의 생성자가 인자로 전달받은 값들이 괄호안의 패턴과 정확히 매치될 수 있는지 검사하는 것이다.  
이렇게 패턴 매치를 내포할 수 있다는 건, 스칼라 패턴이 **깊은 매치** *deep match*를 지원한다 뜻이다. 즉, 어떤 패턴이 제공받은 최상위 객체만을 매치시킬 뿐만 아니라, 내포 패턴과 객체의 내용에 대해서도 매치를 시도한다는 뜻이다. 이때 내포된 패턴이 다시 생성자 패턴이 될 수 있기 때문에 이런 패턴 매치로 원하는 깊이까지 객체 내부를 검사할 수 있다.  
예를 들어 다음과 같은 패턴은 최상위 객체가 BinOp인지 검사한다. 그리고 BinOp 객체의 세 번째 인자가 Number 타입의 객체인지 본다. 그 다음, 그 Number 객체의 값 필드가 0인지 검사한다. 이 패턴의 길이는 한 줄이지만, 실제로는 객체 트리 구조를 세 단계 내려가면서 패턴 매치한다. 

```scala
expr match {
  case BinOp("+", e, Number(0)) => println("a deep match")
  case _ =>
}
```

##### 시퀀스 패턴

임의의 케이스 클래스에 대해 패턴 매치를 하는 것처럼, 배열이나 리스트같은 시퀀스 타입에 대해서도 매치시킬 수 있다. 같은 문법을 사용할 수 있지만 패턴 내부에 원하는 개수만큼 원소를 명시할 수 있다. 

```scala
def startsWithZero1(expr: List[Int]) = 
  expr match {
    case List(0, _, _) => println("found it")
    case _ =>
  }
```

길이를 한정하지 않고 시퀀스와 매치하고 싶다면 패턴의 마지막 원소를 _*로 표시하면 된다. 

```scala
def startsWithZero2(expr: List[Int]) =
  expr match {
    case List(0, _*) => println("found it")
    case _ =>
  }
```

##### 튜플 패턴

```scala
def tupleDemo(expr: Any) =
  expr match {
    case (a, b, c)  =>  println("matched " + a + b + c)
    case _ =>
  }

scala> tupleDemo(("a ", 3, "-tuple"))
matched a 3-tuple
```

##### 타입 지정 패턴

타입 검사나 타입 변환을 간편하게 대신하기 위해 **타입 지정 패턴** *typed pattern*을 사용할 수 있다. 

```scala
def generalSize(x: Any) = x match {
  case s: String => s.length
  case m: Map[_, _] => m.size
  case _ => -1
}

scala> generalSize("abc")
res16: Int = 3

scala> generalSize(Map(1 -> 'a', 2 -> 'b'))
res17: Int = 2

scala> generalSize(math.Pi)
res18: Int = -1
```

##### 타입 소거

```scala
def isIntIntMap(x: Any) = x match {
  case m: Map[Int, Int] => true
  case _ => false
}

<console>:9: warning: non-variable type argument Int in type pattern scala.collection.immutable.Map[Int,Int] (the underlying of Map[Int,Int]) is unchecked since it is eliminated by erasure
         case m: Map[Int, Int] => true
                 ^
isIntIntMap: (x: Any)Boolean

scala> isIntIntMap(Map(1 -> 1))
res19: Boolean = true

scala> isIntIntMap(Map("abc" -> "abc"))
res20: Boolean = true

```

스칼라는 자바와 마찬가지로 제네릭에서 **타입 소거** *type erasure* 모델을 사용한다. 이는 런타임이 타입 인자에 대한 정보를 유지하지 않는다는 뜻이다. 결과적으로 실행시에는 어떤 맵 객체를 두 Int 압이르 타입 인자로 받아서 생성한 것인지, 다른 타입을 받아서 생성한 것인지 알 방법이 없다. 시스템이 할 수 있는 일은 어떤 값이 임의의 타입 인자를 받아 생성한 맵이라고 결정하는 것뿐이다. 

타입 소거의 유일한 예외는 배열이다. 배열은 자바뿐 아니라 스칼라에서도 특별 취급하기 때문이다. 배열은 원소 타입과 값을 함께 저장한다. 그래서 배열 타입과 패턴 매치할 수 있다. 

```scala
def isStringArray(x: Any) = x match {
  case a: Array[String] => "yes"
  case _ => "no"
}

scala> val as = Array("abc")
as: Array[String] = Array(abc)

scala> isStringArray(as)
res21: String = yes

scala> val ai = Array(1, 2, 3)
ai: Array[Int] = Array(1, 2, 3)

scala> isStringArray(ai)
res22: String = no
```

##### 변수 바인딩

변수가 하나만 있는 패턴 말고 다른 패턴에 변수를 추가할 수도 있다. 단순히 변수 이름 다음에 @ 기호를 넣고 패턴을 쓰면 된다. 이를 변수 바인딩 패턴이라고 한다. 그 의미는 패턴에 대해 일반적인 방법대로 매치를 시도하고, 그 패턴 매치에 성공하면 변수가 하나 있는 패턴에서처럼 매치된 객체를 변수에 저장하는 것이다. 

```scala
def matchUnOp(expr: Expr) = {
  expr match {
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case _ =>
  }
}

val unopExpr = UnOp("abs", Number(3))

scala> matchUnOp(unopExpr)
res0: Any = ()

scala> matchUnOp(unopExpr)
res1: Any = UnOp(abs,Number(3.0))
```

```scala
def matchUnOp(expr: Expr): Expr = {
  expr match {
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case _ => Var("test")
  }
}

val unopExpr = UnOp("abs", Number(3))

matchUnOp(unopExpr)
res0: Expr = Var(test)

matchUnOp(UnOp("abs", unopExpr))
res1: Expr = UnOp(abs,Number(3.0))
```

#### 패턴 가드

때때로, 문법적인 패턴 매치만으로 정확성이 부족한 경우가 있다. 예를 들면 e + e 와 같이 두 피연산자가 같은 덧셈 연산을 e * 2 처럼 곱셈으로 변경하는 규칙을 만들어야 한다고 가정하자. 즉, Expr 트리 언어에서 다음고 같은 식을  

```scala
BinOp("+", Var("x"), Var("x"))
```

이 규칙에 따라 다음과 같이 바꾸고 싶다. 

```scala
BinOp("*", Var("x"), Number(2))
```

이 규칙을 정의하기 위해 다음과 같이 시도할 수 있다. 

```scala
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, x) => BinOp("*", x, Number(2))
  case _ => e
}

<console>:17: error: x is already defined as value x
         case BinOp("+", x, x) => BinOp("*", x, Number(2))
```

이 매치는 실패할 것이다. 스칼라가 패턴을 **선형** 패턴으로 제한하기 때문이다. 즉, 어떤 패턴 변수가 한 패턴 안에 오직 한 번만 나와야 한다. 그러나 **패턴 가드** *pattern guard*를 사용하면 다음과 같이 match 표현식을 다시 쓸 수 있다. 

```scala
def simplifyAdd(e: Expr) = e match {
  case BinOp("+", x, y) if x == y =>
    BinOp("*", x, Number(2))
  case _ => e
}
```

패턴 가드는 패턴 뒤에 오고 if로 시작한다. 패턴 안에 있는 변수를 참조하는 어떤 불리언 표현식도 가드로 사용할 수 있다. 패턴에 가드가 있으면 가드가 true가 될 때만 매치에 성공하낟. 그러므로 위의 첫 번째 케이스 문은 두 피연산자가 같은 이진 연산에만 매치될 것이다.  

가드 패턴의 다른 예는 다음과 같다. 

```scala
// match only positive integers
case n: Int if 0 < n => ...  

// match only strings starting with the letter 'a'
case s: String if s(0) == 'a' => ... 
```

#### 패턴 겹침

코드에 있는 순서대로 패턴을 매치시킨다. 다음 코드에 있는 simplifyAdd는 case 문의 순서가 중요하다는 사실을 보여주는 예이다. 

```scala

def simplifyTop(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e))  => e   // Double negation
  case BinOp("+", e, Number(0)) => e   // Adding zero
  case BinOp("*", e, Number(1)) => e   // Multiplying by one
  case _ => expr
}

def simplifyAll(expr: Expr): Expr = expr match {
  case UnOp("-", UnOp("-", e)) =>
    simplifyAll(e)   // '-' is its own inverse
  case BinOp("+", e, Number(0)) =>
    simplifyAll(e)   // '0' is a neutral element for '+'
  case BinOp("*", e, Number(1)) =>
    simplifyAll(e)   // '1' is a neutral element for '*'
  case UnOp(op, e) =>
    UnOp(op, simplifyAll(e))
  case BinOp(op, l, r) =>
    BinOp(op, simplifyAll(l), simplifyAll(r))
  case _ => expr
}
```

위의 simplifyAll은 simplifyTop과 달리 산술 표현식의 최상위 수준뿐 아니라 모든 부분에 간소화 규칙을 적용할 것이다. simplifyTop에다 두 가지 case를 더 추가하면 그렇게 할 수 있다.  
case UnOp(op, e) =>  
case BinOp(op, l, r) =>

네 번째 case는 case UnOp(op, e) 패턴이다. 따라서 모든 단항 연산과 매치된다. 단항 연산의 연산자나 유일한 피연산자는 어느 것이나 될 수 있고, 각각은 패턴 변수 op와 e에 바인딩된다. 이 경우 다시 피연산자 e에 재귀적으로 simplifyAll을 적용한 결과와 원래의 단항 연산자를 사용해 단항 연산 산술식을 다시 만들어낸다.  
다선 번째 case의 BinOp도 비슷한다. 이 case 문은 모든 이항 연산을 처리하는 case문이다. 이 case 문에서는 두 피연산자에 재귀적으로 simplifyAll을 적용한다.  
이 예에서, 모든 경우를 처리하는 case 문이 더 자세한 규칙의 다음 위치에 있는 것이 중요하다. 만약 순서를 바꾸면 모든 경우와 매치되는 case 문으로 인해 구체적인 경우는 아예 매치 시도 자체를 하지 않을 것이다. 대부분의 경우 컴파일러가 이에 대해 경고를 표시할 것이다. 

예로 여기에 컴파일할 수 없는 match 식이 있다. 첫 번째 case 문이 두 번째 case 문이 매치할 수 있는 것을 모두 매치해버리기 때문이다. 

```scala
def simplifyBad(expr: Expr): Expr = expr match {
  case UnOp(op, e) => UnOp(op, simplifyBad(e))
  case UnOp("-", UnOp("-", e)) => e
}

<console>:17: warning: unreachable code
                case UnOp("-", UnOp("-", e)) => e
                                                ^
<console>:15: warning: match may not be exhaustive.
It would fail on the following inputs: BinOp(_, _, _), Number(_), Var(_)
       def simplifyBad(expr: Expr): Expr = expr match {
                                           ^
```

#### 봉인한 클래스 

패턴 매치를 작성할 때 마다 모든 가능한 경우를 다 다뤘는지 확신할 필요가 있다. 물론 match 표현식의 마지막에 디폴트 케이스를 추가해서 할 수도 있지만, 이는 합리적인 디폴트 동작이 있을 때만 적용 가능하다. 그런 동작이 없다면 어떻게 해야 될까? 어떻게 해야 모든 가능성을 다 처리했다는 안전한 느낌을 받을 수 있을까?  

실제로, match 식에서 놓친 패턴 조합이 있는지 찾도록 컴파일러에게 도움을 요청할 수 있다. 이를 위해 컴파일러는 어느 것이 가능한지 알 수 있어야 한다. 일반적으로 스칼라에서 이는 불가능하다. 임의의 컴파일 단위 *compilation until*에 아무때나 케이스 클래스를 정의할 수 있기 때문이다. 예를 들어, 앞에서 정의했단 네 가지 케이스 클래스 정의가 있는 곳이 아닌 다른 컴파일 단위에서 새로 Expr 클래스 계층에 속하는 다섯 번째 케이스 클래스를 정의하는 것을 막을 방법이 없다.  

대안은 케이스 클래스의 슈퍼클래스를 **봉인한 클래스** *sealed class*로 만드는 것이다. 봉인한 클래스는 그 클래스와 같인 파일이 아닌 곳에서 새로운 서브클래스르 만들 수 없다. 이는 패턴 매치에서 아주 쓸모가 있다. 같은 소스 파일에 정의가 있는 이미 알려진 서브클래스만 고려하면 되기 때문이다. 게다가, 컴파일러의 지원을 더 잘 받을 수 있다. 봉인한 클래스를 상속한 케이스 클래스에 대해 패턴 매치를 시도하면, 컴파일러가 경고 메시지와 함께 농친 패턴 조합을 환기해준다.  

그러므로 패턴 매치를 위한 클래스 계층을 작성한다면 그 계층에 속한 클래스를 봉인하는 것을 고려해야 한다. 단순히 계층에서 최상위 슈퍼클래스 앞에 sealed 키워드를 넣으면 된다. 


```scala
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(name: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
```

가능한 케이스를 몇 개 빼먹고 패턴 매치를 정의하면

```scala
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
}

<console>:16: warning: match may not be exhaustive.
It would fail on the following inputs: BinOp(_, _, _), UnOp(_, _)
       def describe(e: Expr): String = e match {
```
이 경고는 몇 가지 가능한 패턴을 처리하기 않기 때문에 MatchError 예외가 던져질 위험이 있음을 말해준다. 경고는 잠재적인 실행 시점 오류의 근원을 알려준다. 이런 메시지는 프로그램을 바르게 만들기 위해 기꺼이 받아들일 만하다.  

그러나 상황에 따라서는 컴파일러의 이런 경고 메시지가 너무 심할 때가 있다. 예를 들어, 문맥상 반드시 Number나 Var일 수밖에 없는 식에만 위의 describe 메소드를 적용하는 경우를 생각해보자. 이런 경우 실제로는 MatchError가 전혀 발생하지 않을 것임을 장담할 수 있따. 컴파일 시 경고를 보기 싫으니까, 다음과 같이 세 번째 case로 나머지 경우를 다 매치시킬 수 있다. 

```scala
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
  case _ => throw new RuntimeException // Should not happen
}
```

물론 이는 잘 동작한다. 하지만 이상적이지는 않다. 단지 컴파일러를 조용하게 하기 위해, 결코 실행되지 않을 코드를 추가하는 건 그다지 기분 좋은 일이 아니다.  

더 간단한 대안은 매치의 셀렉터에 @unchecked 애노테이션을 추가하는 것이다. 그러면 다음과 같이 된다. 

```scala
def describe(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
}
```

어던 match 문의 셀렉터에 이 애노테이션을 붙이면, 그 match 문의 case 문이 모든 패턴을 다 다루는지 검사하는 것을 생랙한다. 

#### Option 타입

스칼라에는 Option이라는 표준 타입이 있다. 이 타입은 선택적인 값을 표현하며 두 가지 형태가 있다. x가 실제 값이면 Some(x) 라는 형태로 값이 있음을 표현하고 없으면 None이라는 객체가 된다.   

스칼라 컬렉션의 몇몇 표준 연산은 선택적인 값을 생성한다. 예를 들어 스칼라 Map의 get 메소드를 인자로 받은 키에 대응하는 값이 있다면 Some(값)을 반환하고 그 키가 없으면 None을 돌려준다. 

```scala
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

scala> capitals get "France"
res0: Option[String] = Some(Paris)

scala> capitals get "North Pole"
res1: Option[String] = None
```

옵션 값을 분리해내는 가장 일반적인 방법은 패턴 매치다.

```scala
def show(x: Option[String]) = x match {
  case Some(s) => s
  case None => "?"
}

scala> show(capitals get "Japan")
res3: String = Tokyo

scala> show(capitals get "France")
res4: String = Paris

scala> show(capitals get "North Pole")
res5: String = ?
```

#### 패턴은 어디에나 

독립적인 match 표현식뿐 아니라, 스칼라의 여러 곳에서 패턴을 사용할 수 있다. 

##### 변수 정의에서 패턴 사용하기 

val이나 var를 정의할 때 단순 식별자 대신 패턴을 사용할 수 있다. 예를 들면 패턴을 사용해 튜플의 각 원소를 변수에 할당할 수 있다. 

```scala
scala> var myTuple = (123, "abc")
myTuple: (Int, String) = (123,abc)

scala> val (number, string) = myTuple
number: Int = 123
string: String = abc
```

이런 구성요소는 케이스 클래스와 같이 사용할 때 매우 유용하다. 작업 대상 케이스 클래스를 정확히 안다면, 패턴을 사용해 다음과 같이 해체할 수 있다. 

```scala
scala> val exp = BinOp("*", Number(5), Number(1))
exp: BinOp = BinOp(*,Number(5.0),Number(1.0))

scala> val BinOp(op, left, right) = exp
op: String = *
left: Expr = Number(5.0)
right: Expr = Number(1.0)
```

##### case 시퀀스로 부분 함수 만들기

함수 리터럴이 쓰일 수 있는 곳이라면 중괄호 사이에 case의 시퀀스를 넣은 표현식도 쓸 수 있다. 근본적으로 케이스 시퀀스도 함수 리터럴이다. 다만, 좀 더 일반적인 함수일 뿐이다. 진입점 하나에 파라미터 리스트가 있는 일반 함수 대신, 케이스 시퀀스는 여러 진입점이 있고 각 진입점마다 각각 파라미터 목록이 있다. 각각의 case가 함수 진입점이고, 패턴은 파라미터를 명시한다. 각 진입점에 따른 함수 본문은 case의 오른쪽(화살표의 오른쪽)이다. 

```scala
val withDefault: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}
```

이 함수에는 두 가지 case문이 있다. 첫째는 Some과 매치하며 Some 내부의 수를 반환한다. 두 번째는 None과 매치하며 0을 반환한다. 

```scala
scala> withDefault(Some(10))
res8: Int = 10

scala> withDefault(None)
res9: Int = 0
```

이 기능은 32장에서 설명한 actor 라이브러리에서 꽤 유용하다. 전형적인 액터 코드를 보자. 아래 코드에서는 react 메소드에 직접 패턴 매치를 넘긴다. 

```scala
react {
  case (name: String, actor: Actor) => {
    actor ! getip(name)
    act()
  }
  case mag => {
    println("Unhandled message: " + msg)
    act()
  }
}
```

케이스의 시퀀스는 **부분 함수** *partial function*다. 부분 함수에 그 함수가 처리하지 않는 값을 전달해 호출하면 실행 시점 예외가 발생한다. 예를 들어 정수 리스트에서 두 번째 원소를 반환하는 부분 함수가 아래에 있다. 

```scala
val second: List[Int] => Int = {
  case x :: y :: _ => y
}
```

위 코드를 컴파일하면 컴파일러는 매치가 모든 경우를 포괄하지 않는다고 경고를 표시한다. 

```scala
<console>:11: warning: match may not be exhaustive.
It would fail on the following inputs: List(_), Nil
```

원소가 3개인 리스트를 넘기면 함수가 성공한다. 그러나 빈 리스트를 넘기면 실패한다. 

```scala
scala> second(List(1, 2, 3))
res10: Int = 2

scala> second(List())
scala.MatchError: List() (of class scala.collection.immutable.Nil$)
  at .$anonfun$second$1(<console>:12)
  at .$anonfun$second$1$adapted(<console>:11)
  ... 28 elided
```

부분 함수가 정의됐는지 체크하기 원한다면, 우선 컴파일러에세 부분 함수를 가지고 작업한다는 사실을 알려야 한다. List[Int] => Int 타입은 정수 리스트를 받아서 정수를 반환하는 모든 함수를 포함한다. 함수가 부분 함수인지 여부는 상관없다. 정수의 리스트를 받아 정수를 반환하는 부분 함수만을 포함하는 타입은 PartialFunction[List[Int], Int]이다. 이번에는 second를 부분 함수 타입으로 다시 쓴 다음 코드를 보자. 

```scala
val second: PartialFunction[List[Int],Int] = {
  case x :: y :: _ => y
}
```

부분 함수에는 isDefinedAt이라는 메소드가 있다. isDefinedAt 메소드는 부분 함수가 어떤 값에 대해 결과 값을 정의하고 있는지를 알려준다. 여기서 second 함수는 최소한 2개이상의 원소를 포함하는 모든 리스트에 대해 결과를 정의한다. 

```scala
scala> second.isDefinedAt(List(5, 6, 7))
res12: Boolean = true

scala> second.isDefinedAt(List())
res13: Boolean = false
```

부분 함수의 전형적인 예는 앞에서 본 것과 같은 패턴 매치 함수 리터럴이다. 실제로 스칼라 컴파일러는 그런 표현식을 두 번 변환해서 부분 함수로 만든다. 먼저 실제 함수 구현으로 패턴 매치를 변환하고(apply() 메소드), 두 번째로 정의됐지는 여부를 검사하기 우해 패턴 매치를 변환한다(isDefinedAt() 메소드). 예를 들어 위의 함수 리터럴 {  case x :: y :: _ => y} 는 다음과 같은 부분 함수 값이 된다. 

```scala
new PartialFunction[List[Int], Int] {
  def apply(xs: List[Int]) = xs match {
    case x :: y :: _ => y
  }
  def isDefinedAt(xs: List[Int]) = xs match {
    case x :: y :: _ => true
    case _ => false
  }
}
```

어떤 함수 리터럴의 타입이 PartialFunction이면 이런 변환을 수행한다. 하지만 타입이 Function1이거나 타입 표기가 없으면 함수 리터럴을 완전한 함수로 변환한다.  

일반적으로 가능하다면 완전한 함수를 사용하는 편이 좋다. 부분 함수를 사용하면 컴파일러가 도와줄 수 없는 실행 시점 오류가 발생할 수도 있기 때문이다. 그렇지만 때로 부분 함수가 정말 도움이 되는 경우가 있다. 그런 경우 부분 함수가 처리할 수 없는 값을 넘기는 일이 없도록 확실히 해야 할 것이다. 아니면, 부분 함수 사용을 예상해 만들어진 프레임워크를 사용해서 항상 함수를 호출하기 전에 isDefinedAt 메소드로 호출 시 문제가 없는지 검사하게 만드는 방벋보 있다. 후자의 예가 바로 위의 react이다. react의 인자는 프로그래머가 처리하기 원하는 메시지에 대해서만 동작을 정의한 부분 함수다. 

#### for 표현식에서 패턴 사용하기

다음 코드처럼 for 표현식 안에 패턴을 사용할 수 있다. 이 for 표현식은 capitals 맵에서 모든 키/값 쌍을 가져온다. 각 튜플은 country와 city 변수가 있는 (country, city) 패턴과 매치된다.

```scala
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

for ((country, city) <- capitals)
  println("The capital of " + country + " is " + city)

The capital of France is Paris
The capital of Japan is Tokyo
```

위 코드의 패턴은 매치가 결코 실패하는 일이 없기 때문에 특별하다. 실제로 capitals는 튜플의 시퀀스를 내놓는다. 따라서 이들은 튜플 패턴과 정확히 매치할 수 있다. 그러나 생성한 값과 패턴이 매치하지 않는 것도 마찬가지로 가능하다. 

```scala
scala> val results = List(Some("apple"), None, Some("orange"))
results: List[Option[String]] = List(Some(apple), None, Some(orange))

scala>

scala> for (Some(fruit) <- results) println(fruit)
apple
orange
```

위 코드를 보면 알 수 있듯이, 생성한 값 중 패턴과 일치하지 않는 값은 버린다. 예를 들어 results 리스트의 두 번째 원소인 None은 Some(fruit) 패턴에 맞지 않는다. 

