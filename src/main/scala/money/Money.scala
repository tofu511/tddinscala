package money

sealed abstract class Money {
  val amount: Int
  def times(multiplier: Int): Money
}

object Money {
  def dollar(amount: Int): Money = Dollar(amount)
  def franc(amount: Int): Money = Franc(amount)
}

case class Dollar(override val amount: Int) extends Money {
  override def times(multiplier: Int): Money = Dollar(amount * multiplier)
}

case class Franc(override val amount: Int) extends Money {
  override def times(multiplier: Int): Money = Franc(amount * multiplier)
}