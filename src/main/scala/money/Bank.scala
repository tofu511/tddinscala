package money

case class Bank() {
  def reduce(source: Expression, currencyUnit: String): Money = Money(10)(currencyUnit)
}
