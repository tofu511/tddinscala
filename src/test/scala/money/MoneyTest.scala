package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("dollar multiplication") {
    val five = Dollar(5)
    assert(Dollar(10) === five.times(2))
    assert(Dollar(15) === five.times(3))
  }

  test("equality") {
    assert(Dollar(5) === Dollar(5))
    assert(Dollar(5) !== Dollar(6))
    assert(Franc(5) === Franc(5))
    assert(Franc(5) !== Franc(6))
    assert(Dollar(5) !== Franc(5))
  }

  test("franc multiplication") {
    val five = Franc(5)
    assert(Franc(10) === five.times(2))
    assert(Franc(15) === five.times(3))
  }
}

case class Dollar(override val amount: Int) extends Money {
  override def times(multiplier: Int): Dollar = Dollar(amount * multiplier)
}

case class Franc(override val amount: Int) extends Money {
  override def times(multiplier: Int): Franc = Franc(amount * multiplier)
}

sealed trait Money {
  val amount: Int
  def times(multiplier: Int): Money
}
