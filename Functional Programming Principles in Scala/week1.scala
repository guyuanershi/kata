package week1

/**
 * Created by guyu on 1/23/2015.
 */
object fun extends App{
  //
  // pascal triangle
  //
  def pascal(c: Int, r: Int): Int = {
    def pascalIterator(c: Int, r: Int):Int = {
      if (c == 0 || c == r) return 1
      pascalIterator(c - 1, r - 1) + pascalIterator(c, r - 1) //A + B
    }
    pascalIterator(c, r)
  }

  // balance
  //
  def balance(char: List[Char]): Boolean = {
    def subBlance(char: List[Char], count: Int): Boolean = {
      if (char.isEmpty && count == 0) return true
      if (char.isEmpty && count != 0) return false
      if (char.head == ')' && count == 0) return false

      if (char.head == '(')
      {
        subBlance(char.tail, count + 1)
      }
      else if (char.head == ')')
      {
        subBlance(char.tail, count - 1)
      }
      else
      {
        subBlance(char.tail, count)
      }
    }

    return subBlance(char, 0)
  }

  //println(balance("(if (zero? x) max (/ 1 x))())(".toList))

  //reduce tree
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) return 1
    else if (money < 0 || coins.length == 0) return 0
    else
    {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }

    val list = List(1,5, 10, 25, 50)
    println(countChange(100, list))
  }

