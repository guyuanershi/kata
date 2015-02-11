/**
 * Created by guyu on 2/10/2015.
 */
package week2

import org.scalatest._
import FunSet._

class SetSpec extends FlatSpec{

  def fixture = new {
    val set1 = FunSet.singletonSet(1)
    val set2 = FunSet.singletonSet(2)
    val set3 = FunSet.singletonSet(3)
  }

  "SingleSet 1" should "have a data 1" in {
    val set = FunSet.singletonSet(1)
    assert(FunSet.contains(set, 1))
  }

  "Union Set(1) and Set(2)" should "have both 1 and 2" in {
    val s = FunSet.union(fixture.set1, fixture.set2)
    assert(FunSet.contains(s, 1))
    assert(FunSet.contains(s, 2))
    assert(!FunSet.contains(s, 3))
  }

  "Intersect Set(1,2) and Set(2,3)" should "have 2" in {
    val s12 = FunSet.union(fixture.set1, fixture.set2)
    val s23 = FunSet.union(fixture.set2, fixture.set3)
    val s = FunSet.intersect(s12, s23)
    assert(FunSet.contains(s, 2))
    assert(!FunSet.contains(s, 1))
    assert(!FunSet.contains(s, 3))
  }

  "Diff Set(1,2) and Set(2,3)" should "have 1" in {
    val s12 = FunSet.union(fixture.set1, fixture.set2)
    val s23 = FunSet.union(fixture.set2, fixture.set3)
    val s = FunSet.diff(s12, s23)
    assert(FunSet.contains(s, 1))
    assert(!FunSet.contains(s, 2))
    assert(!FunSet.contains(s, 3))
  }

  "Filter Set(1,2) by 2" should "have 2" in {
    val s12 = FunSet.union(fixture.set1, fixture.set2)
    val s = FunSet.filter(s12, x => x > 1)
    assert(FunSet.contains(s, 2))
    assert(!FunSet.contains(s, 1))
  }

  "Forall Set(1,2,3)" should "all bigger than 0" in {
    val s = FunSet.union(FunSet.union(fixture.set1, fixture.set2), fixture.set3)
    assert(FunSet.forall(s, x => x > 0))
    assert(!FunSet.forall(s, x => x > 1))
  }
}
