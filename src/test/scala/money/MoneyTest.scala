package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  val USD: String = "USD"
  val CHF: String = "CHF"

  test("dollar multiplication") {
    implicit val currencyUnit: String = USD
    val five: Money = Money(5)
    assert(Money(10) === five.times(2))
    assert(Money(15) === five.times(3))
  }

  test("dollar equality") {
    implicit val currencyUnit: String = USD
    assert(Money(5) === Money(5))
    assert(Money(5) !== Money(6))
  }

  test("franc equality") {
    implicit val currencyUnit: String = CHF
    assert(Money(5) === Money(5))
    assert(Money(5) !== Money(6))
  }

  test("different currency unit equality") {
    assert(Money(5)(USD) !== Money(5)(CHF))
  }

  test("franc multiplication") {
    implicit val currencyUnit: String = CHF
    val five: Money = Money(5)
    assert(Money(10) === five.times(2))
    assert(Money(15) === five.times(3))
  }

  test("currency unit") {
    assert("USD" === Money(1)(USD).currencyUnit)
    assert("CHF" === Money(1)(CHF).currencyUnit)
  }
}
