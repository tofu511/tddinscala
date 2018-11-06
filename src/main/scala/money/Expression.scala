package money

trait Expression {
  def reduce(to: String): Money
}

case class Sum(augend: Money, addend: Money) extends Expression {
  def reduce(to: String): Money = Money(augend.amount + addend.amount)(to)
}
