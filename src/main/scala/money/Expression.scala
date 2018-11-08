package money

trait Expression {
  def plus(addend: Expression): Expression
  def reduce(bank: Bank, to: String): Money
}

case class Sum(augend: Expression, addend: Expression) extends Expression {
  def reduce(bank: Bank, to: String): Money = {
    val totalAmount: Int = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount
    Money(totalAmount)(to)
  }

  def plus(addend: Expression): Expression = ???
}
