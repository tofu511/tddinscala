package money

sealed trait Money extends Expression {
  implicit val currencyUnit: String
  def amount: Int
  def times(multiplier: Int): Money = Money(amount * multiplier)(currencyUnit)
  def plus(added: Money): Expression = Money(amount + added.amount)
}

object Money {
  def apply(amount: Int)(implicit currencyUnit: String): Money = currencyUnit match {
    case "USD" => Dollar(amount)
    case "CHF" => Franc(amount)
  }
}

case class Dollar(private val dollarAmount: Int) extends Money {
  override def amount: Int = dollarAmount
  implicit val currencyUnit: String = "USD"
}

case class Franc(private val francAmount: Int) extends Money {
  override def amount: Int = francAmount
  implicit val currencyUnit: String = "CHF"
}