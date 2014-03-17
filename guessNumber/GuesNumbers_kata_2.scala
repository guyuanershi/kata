package yuan

/**
 * Created by guyuanershi on 3/17/14.
 */

import scala.util._

object GuessNumber {
  var anwser = ""

  def main(args: Array[String]) {
    generateAnwser()
    println("Please input four non-repeat numbers:")
    var i = 1
    while (i < 7) {
      println("This is your " + i + " try =>")
      val input = readLine()
      if (input.length != 4)
        println("number only between 1000 - 9999")
      else {
        val result = guess(input)
        println(result)
        if (result == "4A0B") {
          println("You Win!")
          return
        }
        i = i + 1
      }
    }
    println("You Lose!")
  }

  def guess(iNumber: String) = {
    val nums = findRightNumber(iNumber)
    val bNumber = nums.length
    val aNumber = nums.map(c => findRightPossition(c)).filter(b => b == true).length
    aNumber + "A" + (bNumber - aNumber) + "B"
  }

  def findRightNumber(iNumber: String) = {
    for {
      num <- iNumber
      if (anwser.indexOf(num) != -1)
    } yield (iNumber.indexOf(num), num)
  }

  def findRightPossition(ichar: (Int, Char)) = ichar match {
    case (index, value) => anwser.charAt(index) == value
    case _ => false
  }

  def generateAnwser() = {
    val r = Random
    anwser = (r.nextInt(9999 - 1000) + 1000) + "" //covert to string
  }
}
