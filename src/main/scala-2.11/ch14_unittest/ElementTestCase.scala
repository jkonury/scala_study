package ch14_unittest

import org.junit.Test
import org.junit.Assert.assertEquals

class ElementTestCase {
  @Test
  def sample(): Unit = {
    val e = 10
    assertEquals(10, e)
  }


}
