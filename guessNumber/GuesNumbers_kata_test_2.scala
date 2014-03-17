package yuan

import org.scalatest.FunSuite

/**
 * Created by guyuanershi on 3/17/14.
 */
class GuessNumber$Test extends FunSuite {
  GuessNumber.anwser = "1234"
  test("1234") {
    assert("4A0B" == GuessNumber.guess("1234"))
  }

  test("4321") {
    assert("0A4B" == GuessNumber.guess("4321"))
  }

  test("1324") {
    assert("2A2B" == GuessNumber.guess("1324"))
  }

  test("0012") {
    assert("0A2B" == GuessNumber.guess("0012"))
  }

  test("find number all exist") {
    assert(4 == GuessNumber.findRightNumber("1234").length)
  }

  test("find number 1 exist") {
    assert(1 == GuessNumber.findRightNumber("5551").length)
  }

  test("find number 2 exist") {
    assert(2 == GuessNumber.findRightNumber("2377").length)
  }

  test("find right possiton 1") {
    assert(true == GuessNumber.findRightPossition((0,'1')))
    assert(false == GuessNumber.findRightPossition((2,'1')))
  }
}
