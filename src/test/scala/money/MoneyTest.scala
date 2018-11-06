package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  val USD: String = "USD"
  val CHF: String = "CHF"

  test("money multiplication") {
    implicit val currencyUnit: String = USD
    val five: Money = Money(5)
    assert(Money(10) === five.times(2))
    assert(Money(15) === five.times(3))
  }

  test("money equality") {
    implicit val currencyUnit: String = USD
    assert(Money(5) === Money(5))
    assert(Money(5) !== Money(6))
  }

  test("different currency unit equality") {
    assert(Money(5)(USD) !== Money(5)(CHF))
  }

  test("currency unit") {
    assert("USD" === Money(1)(USD).currencyUnit)
    assert("CHF" === Money(1)(CHF).currencyUnit)
  }

  test("simple addition") {
    implicit val currencyUnit: String = USD
    val five: Money = Money(5)
    val sum: Expression = five.plus(five)
    val bank: Bank = Bank()
    val reduced: Money = bank.reduce(sum, USD)
    assert(Money(10) === reduced)
  }

}
