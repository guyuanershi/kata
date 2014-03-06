package sc

/**
 * Created by guyuanershi on 3/5/14.
 */


class StringCalculator {
  val PATTEN = """^//\[(.+)]\\n(.+)""".r
  val REGCHARS = """.$|()[{^?*+\"""

  def add(numbers: String): Int = {
    numbers match {
      case "" => 0
      case PATTEN(dilt, value) => sum(value, recombine(dilt))
      case _ => sum(numbers, """,|\n""")
    }
  }

  def sum(numbers: String, dilt: String): Int = {
    var sum = 0
    var negs = ""

    numbers.split(dilt).map(n => n.toInt match {
      case neg if neg < 0 => negs = negs + "," + n
      case pos if pos <= 1000 => sum = sum + pos
      case pos if pos > 1000 => sum
    })

    if (!negs.isEmpty){
      throw new IllegalArgumentException("negatives are not allowed:" + negs.drop(1))
    }
    sum
  }

  def recombine(dilt: String) = {
    var _del = ""
    var nonEmptyDilts = dilt.split("]\\[").filter(f => !f.isEmpty)
    for(d <- nonEmptyDilts) {
      _del = _del + "|" + recombineRegChars(d)
    }
    _del.drop(1)
  }

  def recombineRegChars(deli: String) = {
    var _del = ""
    for(d <- deli){
      if (hasRegChar(d))
        _del = _del + "\\" + d
      else
        _del = _del + d
    }
    _del
  }

  def hasRegChar(c: Char) = {
    REGCHARS.indexOf(c) != -1
  }
}
