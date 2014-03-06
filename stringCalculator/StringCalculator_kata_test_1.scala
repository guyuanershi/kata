/**
 * Created by guyuanershi on 3/5/14.
 */
import sc._
import org.scalatest._


class StringCalculatorTest extends org.scalatest.FunSuite {
  val sc = new StringCalculator()

  test("empty string") {
    assert(0 == sc.add(""))
  }

  test("string one") {
    assert(1 == sc.add("1"))
  }

  test("string (1,2), the sum is 3") {
    assert(3 == sc.add("1,2"))
  }

  test("string (1,2,3,4,5,6,7,8,9,10), the sum is 55") {
    assert(55 == sc.add("1,2,3,4,5,6,7,8,9,10"))
  }

  test("string use seprator \n") {
    assert(3 == sc.add("1\n2"))
  }

  test("string user \n and ,") {
    assert(6 == sc.add("1\n2,3"))
  }

  test("string with different delimiter") {
    assert(3 == sc.add("""//[;]\n1;2"""))
  }

  test("negative number") {
    val thrown = intercept[IllegalArgumentException] {
      sc.add("-1")
    }
    assert(thrown.getMessage === "negatives are not allowed:-1")
  }

  test("negative numbers: -1, -2, -3") {
    val thrown = intercept[IllegalArgumentException] {
      sc.add("-1,-2,-3")
    }
    assert(thrown.getMessage === "negatives are not allowed:-1,-2,-3")
  }

  test("number bigger than 1000, ignored") {
    assert(2 == sc.add("2,1001"))
  }

  test("delimiters can be any length") {
    assert(6 == sc.add("""//[***]\n1***2***3"""))
  }

  test("multiple delimiters") {
    assert(6 == sc.add("""//[*][%]\n1*2%3"""))
  }

  test("multiple delimiters 2") {
    assert(6 == sc.add("""//[**][%]\n1**2%3"""))
  }
}