package money

trait Expression {
  def times(multiplier: Int): Expression
  def plus(addend: Expression): Expression
  def reduce(bank: Bank, to: CurrencyUnit): Money
}

case class Sum(augend: Expression, addend: Expression) extends Expression {
  def times(multiplier: Int): Expression = Sum(augend.times(multiplier), addend.times(multiplier))

  def reduce(bank: Bank, to: CurrencyUnit): Money = {
    val totalAmount: Int = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount
    Money(totalAmount)(to)
  }

  def plus(addend: Expression): Expression = Sum(this, addend)
}
