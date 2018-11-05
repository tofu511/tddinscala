package money

sealed trait Money {
  def times(multiplier: Int): Money
  def currency: String
}

object Money {
  def dollar(amount: Int): Money = Dollar(amount = amount)
  def franc(amount: Int): Money = Franc(amount = amount)
}

case class Dollar(private val amount: Int) extends Money {
  override def times(multiplier: Int): Money = Money.dollar(amount = amount * multiplier)
  override def currency: String = "USD"
}

case class Franc(private val amount: Int) extends Money {
  override def times(multiplier: Int): Money = Money.franc(amount = amount * multiplier)
  override def currency: String = "CHF"
}