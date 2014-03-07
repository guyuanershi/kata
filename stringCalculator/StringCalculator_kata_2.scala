package yuan

/**
 * Created by guyuanershi on 3/7/14.
 */
object StringCalculator {
  val PATTEN = """^//\[(.+)]\\n(.+)""".r
  val REGCHARS = ".$()[{^?*+"

  def Add(numbers: String) = numbers match {
      case "" => 0
      case PATTEN(del, value) => sum(value, del)
      case _ => sum(numbers, ",|\\n")
  }


  def sum(value: String, del: String) = {
    val ndel = normalizeDelimiter(del)
    val numbers = value.split(ndel).map(n => n.toInt)
    val leftNum = checkNumber(numbers)
    leftNum.reduceRight(_+_)
  }

  def normalizeDelimiter(del: String) = {
    del.split("]\\[").map(normalizeReg(_)).reduceLeft(_+"|"+_)
  }

  def normalizeReg(del: String): String = {
    del.map(c => c match {
      case c if REGCHARS.indexOf(c) != -1 => "\\" + c
      case _ => "" + c
    }).reduceLeft(_+_)
  }

  def checkNumber(numbers: Array[Int]) = {
    checkNegative(numbers)
    removeBiggerNumber(numbers)
  }

  def checkNegative(numbers: Array[Int]) = {
    val negatives = numbers.filter(n => n < 0)
    if (negatives.length > 0)
      throw new IllegalArgumentException("negatives are not allowed:" + negatives.foldLeft("")(_+","+_).drop(1))
  }

  def removeBiggerNumber(numbers: Array[Int]) = {
    numbers.filter(n => n <= 1000)
  }
}
