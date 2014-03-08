package yuan

/**
 * Created by guyuanershi on 3/8/14.
 */
object StringCalculator {
  val PATTEN = """//\[(.+)]\n(.+)""".r
  val REGCHARS = ".$ | ()[ {^?*+\\"

  def add(numbers: String) = numbers match {
    case "" => 0
    case _ => sum(numbers)
  }

  def sum(numbers: String) = {
    val allnums = splitString(numbers)
    val intNums = allnums.map(n => n.toInt)
    val lastnums = checkNum(intNums)
    lastnums.reduceLeft(_ + _)
  }

  def splitString(numbers: String) = numbers match {
    case PATTEN(del, value) => value.split(norminalize(del))
    case _ => numbers.split(",|\n")
  }

  def norminalize(del: String) = {
    val ndel = del.split("]\\[")
    ndel.map(d => norminalizeRegChars(d)).reduceLeft(_ + "|" + _)
  }

  def norminalizeRegChars(del: String) = {
    del.map(d => d match {
      case d if REGCHARS.indexOf(d) != -1 => "\\" + d
      case _ => d
    }).foldLeft("")(_ + _)
  }

  def checkNum(numbers: Array[Int]) = {
    val posnumbs = checkNegNum(numbers)
    checkBiggerNum(posnumbs)
  }

  def checkNegNum(numbers: Array[Int]) = numbers.filter(n => n < 0) match {
      case negs if negs.length > 0 => throw new IllegalArgumentException("negatives are not allowed:" + negs.foldLeft("")(_ + "," + _).drop(1))
      case poss => numbers
  }

  def checkBiggerNum(numbers: Array[Int]) = {
    numbers.filter(n => n <= 1000)
  }
}
