package money

trait Expression {
  def reduce(bank: Bank, to: String): Money
}

case class Sum(augend: Money, addend: Money) extends Expression {
  def reduce(bank: Bank, to: String): Money = Money(augend.amount + addend.amount)(to)
}
