package yuan

/**
 * Created by guyuanershi on 3/10/14.
 */
object StringCalculator {
  val PATTEN = """//(.+)\n(.+)""".r
  val REGCHARS = ".$|()[{^?*+\\";

  def add(numbers: String) = numbers match {
    case "" => 0
    case _ => sum(numbers)
  }

  def sum(numbers: String) = {
    val intNumbers = numbers match {
      case PATTEN(del, value) => splitNumberString(value, del)(reform = reformByRegs)
      case _ => splitNumberString(numbers, ",|\n")(nonReform => nonReform)
    }
    intNumbers.reduceLeft(_+_)
  }

  def splitNumberString(numStr: String, del: String)(reform: String => String) = {
    val nonEmptyDel = getNonEmptyDel(del)
    val reformedDel = nonEmptyDel.map(s => reform(s)).reduceLeft(_+"|"+_)
    val intNumbers = numStr.split(reformedDel).map(n => n.toInt)
    filterNumb(intNumbers)
  }

  def filterNumb(nums: Array[Int]) = {
    checkNegative(nums)
    noBigNumber(nums)
  }

  def getNonEmptyDel(del: String) = {
    del.split("]|\\[").filter(s => !s.isEmpty)
  }

  def checkNegative(nums: Array[Int]) = {
    val negs = nums.filter(n => n < 0)
    if (negs.length > 0)
      throw new IllegalArgumentException("negatives are not allowed:" + negs.foldLeft("")(_+","+_).drop(1))
  }

  def noBigNumber(nums: Array[Int]) = {
    nums.filter(n => n < 1000)
  }

  def reformByRegs(del: String) = {
    del.map(c => c match {
      case c if REGCHARS.indexOf(c) != -1 => "\\" + c
      case _ => "" + c
    }).reduceLeft(_+_)
  }

}
