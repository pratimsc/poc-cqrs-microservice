package model.command

import service.command.{Command, Success}
import technical.rule.RulesFactory

case class MakeAPaymentCommand(paymentMode: String,
                               amount: Int,
                               currency: String,
                               originatingAccountNumber: String,
                               beneficiaryAccountNumber: String,
                               narratives: Seq[String]) extends Command {
  override def techincalValidationRules = Seq(
    RulesFactory.isValidPaymentAmount(amount),
    RulesFactory.isValidCurrency(currency)
  )

  override def domainValidationRules = Seq()

  override def domainProcessing = Success
}
