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
  }

  test("franc multiplication") {
    val five = Franc(5)
    assert(Franc(10) === five.times(2))
    assert(Franc(15) === five.times(3))
  }
}

case class Dollar(amount: Int) {
  def times(multiplier: Int): Dollar = Dollar(amount * multiplier)
}

case class Franc(amount: Int) {
  def times(multiplier: Int): Franc = Franc(amount * multiplier)
}
