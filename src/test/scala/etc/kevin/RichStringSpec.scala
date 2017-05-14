package etc.kevin

import org.scalatest.{Matchers, WordSpec}
import RichString.ToUncapitalize

class RichStringSpec extends WordSpec with Matchers {

  "RichString" when {
    val input = "Test"
    s"""uncapitalize("$input")""" should {
      val expected = "test"

      s"""return "$expected"""" in {
        val actual = RichString.uncapitalize(input)
        assert(actual === expected)

        actual should be (expected)
      }
    }

    val input2 = "TestSomething"
    s"""uncapitalize("$input2")""" should {
      val expected = "testSomething"

      s"""return "$expected"""" in {
        val actual = RichString.uncapitalize(input2)
        actual should be (expected)
      }
    }

    val input3 = "test"
    s"""uncapitalize("$input3")""" should {
      val expected = "test"

      s"""return "$expected"""" in {
        val actual = RichString.uncapitalize(input3)
        actual should be (expected)
      }
    }

    val input4 = ""
    s""""$input4".uncapitalize""" should {
      val expected = ""

      s"""return "$expected"""" in {
        val actual = input4.uncapitalize
        actual should be (expected)
      }
    }

    val input5: String = null
    s"""$input5.uncapitalize""" should {
      """return "throw NullPointerException" """ in {
        a [NullPointerException] should be thrownBy input5.uncapitalize
      }
    }
  }
}
