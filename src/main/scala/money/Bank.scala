package money

case class Bank(exchange: Exchange) {
  def reduce(source: Expression, to: String): Money = source.reduce(this, to)
}

case class Exchange(from: String, to: String, rate: Int = 1) {
  def apply(from: String, to: String, rate: Int): Exchange = {
    if (from == to) Exchange(from, to, 1)
    else Exchange(from, to, rate)
  }
}