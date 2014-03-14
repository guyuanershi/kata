package yuan

/**
 * Created by guyuanershi on 3/14/14.
 */
object StringCalculator {
  val PATTEN = """//(.+)\n(.+)""".r
  val REGCHARS = ".$|()[{^?*+\\"

  def add(numbers: String) = numbers match {
    case "" => 0
    case PATTEN(delim, value) => evaluate(value, delim)(normilizeDelimiter)
    case _ => evaluate(numbers, ",|\n")(delimiter => delimiter)
  }

  def evaluate(value: String, delim: String)(normilize: String => String) = {
    val strNumbers = splitNumbers(value, normilize(delim))
    val intNumbers = toIntArray(strNumbers)
    val finalNumbers = checkNumbers(intNumbers)
    sum(finalNumbers)
  }

  def splitNumbers(numbers: String, delim: String) = {
    numbers.split(delim)
  }

  def normilizeDelimiter(delim: String) = {
    val noEmptydelims = delim.split("]|\\[").filter(c => !c.isEmpty)
    noEmptydelims.map(del =>
      del.map(c => c match {
        case c if REGCHARS.indexOf(c) != -1 => "\\" + c
        case _ => "" + c
      }).reduceLeft(_ + _)
    ).reduceLeft(_ + "|" + _)
  }

  def toIntArray(numbers: Array[String]) = {
    numbers.map(n => n.toInt)
  }

  def checkNumbers(numbers: Array[Int]) = {
    val negs = numbers.filter(n => n < 0)
    if (negs.length > 0)
      throw new IllegalArgumentException("negatives are not allowed:" + negs.foldLeft("")(_ + "," + _).drop(1))

    numbers.filter(n => n < 1000)
  }

  def sum(numbers: Array[Int]) = {
    numbers.reduceLeft(_ + _)
  }
}
