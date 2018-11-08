package money

import org.scalatest.FunSuite

class MoneyTest extends FunSuite {

  test("money multiplication") {
    implicit val currencyUnit: CurrencyUnit = USD
    val five: Money = Money(5)
    assert(Money(10) === five.times(2))
    assert(Money(15) === five.times(3))
  }

  test("money equality") {
    implicit val currencyUnit: CurrencyUnit = USD
    assert(Money(5) === Money(5))
    assert(Money(5) !== Money(6))
  }

  test("different currency unit equality") {
    assert(Money(5)(USD) !== Money(5)(CHF))
  }

  test("currency unit") {
    assert(USD === Money(1)(USD).currencyUnit)
    assert(CHF === Money(1)(CHF).currencyUnit)
  }

  test("simple addition") {
    implicit val currencyUnit: CurrencyUnit = USD
    val five: Money = Money(5)
    val sum: Expression = five.plus(five)
    val bank: Bank = Bank(Exchange(USD, USD))
    val reduced: Money = bank.reduce(sum, USD)
    assert(Money(10) === reduced)
  }

  test("plus returns sum") {
    implicit val currencyUnit: CurrencyUnit = USD
    val five: Money = Money(5)
    val result: Expression = five.plus(five)
    val sum: Sum = result.asInstanceOf[Sum]
    assert(five === sum.augend)
    assert(five === sum.addend)
  }

  test("reduces sum") {
    implicit val currencyUnit: CurrencyUnit = USD
    val sum: Sum = Sum(Money(5), Money(3))
    val result: Money = Bank(Exchange(USD, USD)).reduce(sum, USD)
    assert(Money(8) === result)
  }

  test("reduce money") {
    implicit val currencyUnit: CurrencyUnit = USD
    val result: Money = Bank(Exchange(USD, USD)).reduce(Money(5), USD)
    assert(Money(5) === result)
  }

  test("reduce money different currency") {
    val bank: Bank = Bank(Exchange(from = CHF, to = USD, rate = 2))
    val result: Money = bank.reduce(Money(2)(CHF), USD)
    assert(Money(1)(USD) === result)
  }

  test("mixed addition") {
    val fiveBucks: Expression = Money(5)(USD)
    val tenFrancs: Expression = Money(10)(CHF)
    val bank = Bank(exchange = Exchange(CHF, USD, 2))
    val result: Money = bank.reduce(fiveBucks.plus(tenFrancs), USD)
    assert(Money(10)(USD) === result)
  }

  test("sum plus money") {
    val fiveBucks: Expression = Money(5)(USD)
    val tenFrancs: Expression = Money(10)(CHF)
    val bank: Bank = Bank(Exchange(CHF, USD, 2))
    val sum: Expression = Sum(fiveBucks, tenFrancs).plus(fiveBucks)
    val result: Money = bank.reduce(sum, USD)
    assert(Money(15)(USD) === result)
  }

  test("sum times") {
    val fiveBucks: Expression = Money(5)(USD)
    val tenFrancs: Expression = Money(10)(CHF)
    val bank: Bank = Bank(Exchange(CHF, USD, 2))
    val sum: Expression = Sum(fiveBucks, tenFrancs).times(2)
    val result: Money = bank.reduce(sum, USD)
    assert(Money(20)(USD) === result)
  }

}
