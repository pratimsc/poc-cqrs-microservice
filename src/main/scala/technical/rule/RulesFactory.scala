package technical.rule

import java.util.UUID

import service.command._

object RulesFactory {
  def isValidPaymentAmount(amount: Int) = new Rule {
    override def validate(): Result = if (amount > 0)
      Success
    else
      Failure(Seq(Error(uUID = UUID.randomUUID(), code = "PMT001", title = "Invalid payment amount", detail = s"Amount ${amount} must be greater than 0 (Zero) ")))
  }

  def isValidCurrency(currency: String) = new Rule {
    val CURRENCIES = Seq("GBP", "EUR", "INR", "SAR")

    override def validate(): Result = if (CURRENCIES.contains(currency))
      Success
    else
      Failure(Seq(Error(uUID = UUID.randomUUID(), code = "PMT002", title = "Unsupported currency", detail = s"Currency ${currency} is not supported. Supported currencies are [${CURRENCIES.mkString(",")}] ")))
  }

}
