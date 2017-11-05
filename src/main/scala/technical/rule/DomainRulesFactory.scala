package technical.rule

import java.util.UUID

import service.command._

object DomainRulesFactory {

  def isValidOriginatingAccount(ibanAccount: String) = new Rule {
    val RESIDENT_COUNTRIES = Seq("GB", "IE", "FR", "DE")

    override def validate(): Result = {
      val countryOfAccountRisidence = ibanAccount.substring(0, 2)
      RESIDENT_COUNTRIES.contains(countryOfAccountRisidence) match {
        case true => Success
        case false => Failure(Seq(Error(UUID.randomUUID(),
          code = "PMD001",
          title = "Unsupported contry of residence",
          detail = s"Country ${countryOfAccountRisidence} is not supported. Supported countries are [${RESIDENT_COUNTRIES.mkString(",")}]")
        ))
      }
    }
  }

  def notSendingMoneyToSanctionedCountry(destinationAccount: String) = new Rule {
    val SANCTIONED_COUNTRIES = Seq("IR", "CI", "SN")

    override def validate(): Result = {
      val destinationCountry = destinationAccount.substring(0, 2)
      SANCTIONED_COUNTRIES.contains(destinationCountry) match {
        case true => Failure(Seq(Error(UUID.randomUUID(),
          code = "PMD002",
          title = "Unsupported contry of residence",
          detail = s"Country ${destinationCountry} is not supported. Sanctioned countries are [${SANCTIONED_COUNTRIES.mkString(",")}]")
        ))
        case false => Success
      }
    }
  }

}
