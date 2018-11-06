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

  test("plus returns sum") {
    implicit val currencyUnit: String = USD
    val five: Money = Money(5)
    val result: Expression = five.plus(five)
    val sum: Sum = result.asInstanceOf[Sum]
    assert(five === sum.augend)
    assert(five === sum.addend)
  }

  test("reduces sum") {
    implicit val currencyUnit: String = USD
    val sum: Sum = Sum(Money(5), Money(3))
    val result: Money = Bank().reduce(sum, USD)
    assert(Money(8) === result)
  }

  test("reduce money") {
    implicit val currencyUnit: String = USD
    val result: Money = Bank().reduce(Money(5), USD)
    assert(Money(5) === result)
  }
}
