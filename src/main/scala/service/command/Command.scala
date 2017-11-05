package service.command

trait Command {

  def techincalValidationRules: Seq[Rule]

  def domainValidationRules: Seq[Rule]

  def domainProcessing: Result
}


