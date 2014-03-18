package yuan

import org.scalatest.FunSuite

/**
 * Created by guyuanershi on 3/18/14.
 */
class GuessNumber$Test extends FunSuite {
  GuessNumber.answer = "1234"

  test("right numbers 1") {
    assert(1 == GuessNumber.getRightNumbers("1567").length)
  }

  test("right numbers 2") {
    assert(2 == GuessNumber.getRightNumbers("3498").length)
  }

  test("right numbers 4") {
    assert(4 == GuessNumber.getRightNumbers("4321").length)
  }

  test("right possition") {
    assert(true == GuessNumber.getRightPossitionNumber((0, '1')))
    assert(false == GuessNumber.getRightPossitionNumber((0, '2')))
    assert(true == GuessNumber.getRightPossitionNumber((3, '4')))
  }

  test("0A4B") {
    assert("0A4B" == GuessNumber.guessNumber("4321"))
  }

  test("1A2B") {
    assert("1A2B" == GuessNumber.guessNumber("1326"))
  }

  test("4A0B") {
    assert("4A0B" == GuessNumber.guessNumber("1234"))
  }
}
