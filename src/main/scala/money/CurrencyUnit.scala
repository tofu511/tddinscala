package money

sealed abstract class CurrencyUnit

case object USD extends CurrencyUnit
case object CHF extends CurrencyUnit