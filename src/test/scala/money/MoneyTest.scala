package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("multiply 5 by 2 should be 10") {
    val ten = Dollar(5).times(2)
    assert(10 == ten.amount)
  }

  test("multiply 10 by 3 should be 30") {
    val thirty = Dollar(10).times(3)
    assert(30 == thirty.amount)
  }
}

case class Dollar(amount: Int) {
  def times(multiplier: Int): Dollar = Dollar(amount * multiplier)
}
