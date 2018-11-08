package money

case class Bank(exchange: Exchange) {
  def reduce(source: Expression, to: CurrencyUnit): Money = source.reduce(this, to)
}

case class Exchange(from: CurrencyUnit, to: CurrencyUnit, rate: Int = 1) {
  def apply(from: CurrencyUnit, to: CurrencyUnit, rate: Int): Exchange = {
    if (from == to) Exchange(from, to, 1)
    else Exchange(from, to, rate)
  }
}