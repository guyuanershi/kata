package yuan

import org.scalatest.FunSuite

/**
 * Created by guyuanershi on 3/11/14.
 */
class StringCalculator$Test extends FunSuite {
  test("empty string") {
    assert(0 == StringCalculator.add(""))
  }

  test("1") {
    assert( 1 == StringCalculator.add("1"))
  }

  test("1,2") {
    assert(3 == StringCalculator.add("1,2"))
  }

  test("1\\n2") {
    assert(3 == StringCalculator.add("1\n2"))
  }

  test("//;\\n1;2") {
    assert(3 == StringCalculator.add("//;\n1;2"))
  }

  test("negative -1") {
    val thrown = intercept[IllegalArgumentException] {
      StringCalculator.add("-1")
    }
    assert(thrown.getMessage == "negatives are not allowed:-1")
  }

  test("negatives -1,2,-3") {
    val thrown = intercept[IllegalArgumentException] {
      StringCalculator.add("-1,2,-3")
    }
    assert(thrown.getMessage == "negatives are not allowed:-1,-3")
  }

  test("no big value than 1000") {
    assert(2 == StringCalculator.add("2,1001"))
  }

  test("//[***]\\n1***2***3") {
    assert(6 == StringCalculator.add("//[***]\n1***2***3"))
  }

  test("//[*][&\\n1*2&3") {
    assert(6 == StringCalculator.add("//[*][&]\n1*2&3"))
  }
}
