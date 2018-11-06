package money

sealed trait Money {
  def amount: Int
  def times(multiplier: Int): Money = Money(amount * multiplier)(currencyUnit)
  implicit val currencyUnit: String
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