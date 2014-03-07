package yuan

import org.scalatest.FunSuite

/**
 * Created by guyuanershi on 3/7/14.
 */
class StringCalculatorTest extends FunSuite {

  test("numbers is empty") {
    assert(0 == StringCalculator.Add(""))
  }

  test("numbers is 1") {
    assert(1 == StringCalculator.Add("1"))
  }

  test("numbers is 1,2") {
    assert(3 == StringCalculator.Add("1,2"))
  }

  test("numbers with \n as delimiter") {
    assert(3 == StringCalculator.Add("1\n2"))
  }

  test("numbers with \n and , as delimiters") {
    assert(6 == StringCalculator.Add("1\n2,3"))
  }

  test("numbers is negative -1") {
    val thrown = intercept[IllegalArgumentException] {
      StringCalculator.Add("-1")
    }
    assert(thrown.getMessage == "negatives are not allowed:-1")
  }

  test("numbers are negative -1,2,-3") {
    val thrown = intercept[IllegalArgumentException] {
      StringCalculator.Add("-1,-3")
    }
    assert(thrown.getMessage == "negatives are not allowed:-1,-3")
  }

  test("numbers are larger than 1000") {
    assert(2 == StringCalculator.Add("2,1001"))
  }

  test("using different delimiter") {
    assert(3 == StringCalculator.Add("//[;]\\n1;2"))
  }

  test("using any length of delimiters") {
    assert(6 == StringCalculator.Add("//[***]\\n1***2***3"))
  }

  test("using mulitple delimiters") {
    assert(6 == StringCalculator.Add("//[*][%]\\n1*2%3"))
  }
}
