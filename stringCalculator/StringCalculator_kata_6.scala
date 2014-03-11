package yuan

/**
 * Created by guyuanershi on 3/11/14.
 */
object StringCalculator {
  val PATTEN = """//(.+)\n(.+)""".r
  val REGCHARS = ".$|()[{^?*+\\"

  def add(numbers: String) = numbers match {
    case "" => 0
    case _ => sum(numbers)
  }

  def sum(numbers: String) = {
    val strNumbers = numbers match {
      case PATTEN(del, value) => splitNumbers(value, del)(reformDel = reformDel)
      case _ => splitNumbers(numbers, ",|\n")(nonReformDel => nonReformDel)
    }
    val intNumbers = getIntNumbers(strNumbers)
    clearIntNUmbers(intNumbers).reduceLeft(_ + _)
  }

  def splitNumbers(numbers: String, del: String)(reformDel: String => String) = {
    val norminalizedDels = getDelimiters(del).map(s => reformDel(s)).reduceLeft(_ + "|" + _)
    numbers.split(norminalizedDels)
  }

  def getIntNumbers(numbers: Array[String]) = {
    numbers.map(n => n.toInt)
  }

  def getDelimiters(del: String) = {
    del.split("]|\\[").filter(s => !s.isEmpty)
  }

  def reformDel(del: String) = {
    del.map(c => c match {
      case c if REGCHARS.indexOf(c) != -1 => "\\" + c
      case _ => "" + c
    }).reduceLeft(_ + _)
  }

  def clearIntNUmbers(numbers: Array[Int]) = {
    checkNegNumbers(numbers)
    ignoreBigNumbers(numbers)
  }

  def checkNegNumbers(numbers: Array[Int]) = {
    val negs = numbers.filter(n => n < 0)
    if (negs.length > 0)
      throw new IllegalArgumentException("negatives are not allowed:" + negs.foldLeft("")(_ + "," + _).drop(1))
  }

  def ignoreBigNumbers(numbers: Array[Int]) = {
    numbers.filter(n => n < 1000)
  }
}
