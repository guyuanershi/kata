package week2

/**
 * Created by guyu on 2/3/2015.
 */
object FunSet {

  type Set = Int => Boolean

  def contains(s: Set, elem: Int): Boolean = s(elem)

  def singletonSet(elem: Int): Set = (x: Int) => elem == x

  def union(s: Set, t: Set): Set = (x: Int) => s(x) || t(x)

  def intersect(s: Set, t: Set): Set = (x: Int) => s(x) && t(x)

  def diff(s: Set, t: Set): Set = (x: Int) => s(x) && !t(x)

  def filter(s: Set, p: Int => Boolean): Set = (x: Int) => s(x) && p(x)

  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a >1000) return true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-1000)
  }

  def exist(s: Set, p: Int => Boolean): Boolean = forall(s, p)

  def map(s: Set, f: Int => Int): Set = ???
}
