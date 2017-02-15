val nums = Set(1, 2, 3)
nums + 5
nums - 3
nums ++ List(5, 6)
nums -- List(1, 2)
nums & Set(1, 3, 5, 7)
nums.size
nums.contains(3)
nums contains 3
import scala.collection.mutable
val words = mutable.Set.empty[String]
words += "the"
words -= "the"
words ++ List("do", "re", "mi")
words -- List("do", "re")
words.clear()
