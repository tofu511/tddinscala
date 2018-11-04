package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("multiplication") {
    val five = Dollar(5)
    assert(Dollar(10) === five.times(2))
    assert(Dollar(15) === five.times(3))
  }

  test("equality") {
    assert(Dollar(5) === Dollar(5))
    assert(Dollar(5) !== Dollar(6))
  }
}

case class Dollar(amount: Int) {
  def times(multiplier: Int): Dollar = Dollar(amount * multiplier)
}
