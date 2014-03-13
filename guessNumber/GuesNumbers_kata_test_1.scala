package yuan

import org.scalatest.FunSuite

/**
 * Created by guyuanershi on 3/12/14.
 */
class GuessNumbers$Test extends FunSuite {
  val input = "1234"

  test("0A1B") {
    GuessNumbers.answer = input
    assert("0A1B" == GuessNumbers.guess("9199"))
  }
  test("guess right") {
    GuessNumbers.answer = input
    assert("4A0B" == GuessNumbers.guess(input))
  }

  test("1A3B") {
    GuessNumbers.answer = input
    assert("1A3B" == GuessNumbers.guess("1423"))
  }

  test("2A2B") {
    GuessNumbers.answer = input
    assert("2A2B" == GuessNumbers.guess("1324"))
  }

  test("1A1B") {
    GuessNumbers.answer = "1234"
    assert("1A1B" == GuessNumbers.guess("130"))
  }

}
