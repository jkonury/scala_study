

(x: Int, y: Int) => x + y
val myFunc = (x: Int, y: Int) => x + y
myFunc(1, 2)

def func(x: Int)(y: Int) = x + y

def myPartialFunction(y: Int) = myFunc(1, y)
myPartialFunction(2)

def myPF2 = func(1) _

val myFunc1 = (x: Int) => (y: Int) => x + y

val myPF3 = myFunc1(1)
myPF3(2)

val myAFunc = func(1) _
val myAFunc2 = myFunc1(1)

