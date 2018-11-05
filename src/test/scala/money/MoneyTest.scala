package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("dollar multiplication") {
    val five: Money = Money.dollar(5)
    assert(Money.dollar(10) === five.times(2))
    assert(Money.dollar(15) === five.times(3))
  }

  test("equality") {
    assert(Money.dollar(5) === Money.dollar(5))
    assert(Money.dollar(5) !== Money.dollar(6))
    assert(Money.franc(5) === Money.franc(5))
    assert(Money.franc(5) !== Money.franc(6))
    assert(Money.dollar(5) !== Money.franc(5))
  }

  test("franc multiplication") {
    val five: Money = Money.franc(5)
    assert(Money.franc(10) === five.times(2))
    assert(Money.franc(15) === five.times(3))
  }

  test("currency") {
    assert("USD" === Money.dollar(1).currency)
    assert("CHF" === Money.franc(1).currency)
  }
}
