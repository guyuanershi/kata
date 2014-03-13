package yuan

/**
 * Created by guyuanershi on 3/12/14.
 */

import scala.util._

object GuessNumbers {

  def main(args: Array[String]) {
    println("Guess four non-repeat numbers")
    createAnswer
    //println(answer)

    (1 to 6) foreach {
      i => {
        println(i + " try:")
        val inputs = Console.readLine
        try {
          val value = guess(inputs)
          println(value)
          println("==" * 10)
          if (value == "4A0B") {
            println("You Win!")
            return
          }
        } catch {
          case  ex: StringIndexOutOfBoundsException => println(ex.getMessage)
        }
      }
    }

    println("You Lose!")
    println("Then answer is " + answer)
  }

  def guess(input: String) = {
    if (checkLength(input)) {
      throw new StringIndexOutOfBoundsException("Only accept 4 literals.")
    }
    val b = checkLetter(input)
    val a = checkPossition(b)

    val aNum = a.length
    val bNum = b.length - a.length

    val result = aNum + "A" + bNum + "B"
    result
  }

  def checkLength(input: String) = {
    input.size > 4
  }

  def checkLetter(input: String) = {
    var list: List[(Int, Char)] = List()
    input.toCharArray.map(c => {
      if (answer.indexOf(c) != -1)
        list = (input.indexOf(c), c) :: list
    })
    list
  }

  def checkPossition(input: List[(Int, Char)]) = {
    input.filter(t => answer.charAt(t._1) == t._2)
  }

  def createAnswer = {
    val strings = "0123456789"
    var r = ""
    val random = new Random()
    while(r.length < 4) {
      val rindex = random.nextInt(10)
      val s = strings.charAt(rindex)
      if (r.indexOf(s) == -1)
        r = r + strings.charAt(rindex)
    }
    answer = r
  }

  var answer: String = _
}
