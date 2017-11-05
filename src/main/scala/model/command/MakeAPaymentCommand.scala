package model.command

import service.command.{Command, Success}
import technical.rule.{DomainRulesFactory, TechnicalRulesFactory}

case class MakeAPaymentCommand(paymentMode: String,
                               amount: Int,
                               currency: String,
                               originatingAccountNumber: String,
                               beneficiaryAccountNumber: String,
                               narratives: Seq[String]) extends Command {
  override def techincalValidationRules = Seq(
    TechnicalRulesFactory.isValidPaymentAmount(amount),
    TechnicalRulesFactory.isValidCurrency(currency)
  )

  override def domainValidationRules = Seq(
    DomainRulesFactory.isValidOriginatingAccount(originatingAccountNumber),
    DomainRulesFactory.notSendingMoneyToSanctionedCountry(beneficiaryAccountNumber))

  override def domainProcessing = Success
}
