package money

sealed trait Money extends Expression {
  implicit val currencyUnit: CurrencyUnit
  def amount: Int
  def times(multiplier: Int): Expression = Money(amount * multiplier)(currencyUnit)
  def plus(addend: Expression): Expression = Sum(this, addend)
  def reduce(bank: Bank, to: CurrencyUnit): Money =  {
    currencyUnit match {
      case USD => Money(amount)(to)
      case CHF => Money(amount / bank.exchange.rate)(to)
    }
  }
}

object Money {
  def apply(amount: Int)(implicit currencyUnit: CurrencyUnit): Money = currencyUnit match {
    case USD => Dollar(amount)
    case CHF => Franc(amount)
  }
}

case class Dollar(private val dollarAmount: Int) extends Money {
  override def amount: Int = dollarAmount
  implicit val currencyUnit: CurrencyUnit = USD
}

case class Franc(private val francAmount: Int) extends Money {
  override def amount: Int = francAmount
  implicit val currencyUnit: CurrencyUnit = CHF
}