package yuan

/**
 * Created by guyuanershi on 3/18/14.
 */

import scala.util._

object GuessNumber {
  var answer = ""

  def main(args: Array[String]) {
    generateAnswer()

    println("Please input 4 non-repeat numbers:")

    var i = 1
    while(i < 7) {
      println("this is your " + i + " try:")
      val input = readLine()
      if (input.length != 4)
        println("only support 4 size number")
      else{
        val number = guessNumber(input)
        println(number)
        if (number == "4A0B") {
          println("You Win!")
          return
        }
        i += 1
      }
    }
    println("You Lose! Answer is " + answer)
  }

  def guessNumber(input: String) = {
    val rightNums = getRightNumbers(input)
    val aNum = rightNums.map(n => getRightPossitionNumber(n)).filter(s => s == true).length
    val bNum = rightNums.length
    aNum + "A" + (bNum - aNum) + "B"
    //aNum == 4
  }

  def getRightNumbers(input: String) = {
    for {
      n <- input
      if answer.indexOf(n) != -1
    } yield (input.indexOf(n), n)
  }

  def getRightPossitionNumber(rNum: (Int, Char)) = rNum match {
    case (index, value) => answer.charAt(index) == value
    case _ => false
  }

  def generateAnswer() = {
    var found = false
    while(!found) {
      val _answer = Random.nextInt(9000 - 1000) + 1000 + ""
      if (_answer.groupBy(k => k).filter(kv => kv._2.length > 1).size == 0){
        answer = _answer
        found = true
      }
    }
  }
}
