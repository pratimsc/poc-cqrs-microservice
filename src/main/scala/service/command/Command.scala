package service.command

trait Command {

  def techincalValidationRules: Seq[Rule]

  def domainValidationRules: Seq[Rule]

  def domainProcessing: Result
}

/**
  * Command processor processing all the commands
  */
object CommandProcessor {


  def execute(command: Command): Result = {

    val preprocessResult = validateRules(command.techincalValidationRules) match {
      case f: Failure => f
      case Success => validateRules(command.domainValidationRules)
    }

    preprocessResult match {
      case f: Failure => f
      case Success => command.domainProcessing
    }
  }

  private final def validateRules(rules: Seq[Rule]): Result = {
    val results: Seq[Result] = rules.map(r => r.validate())
    val failures: Seq[Result] = results.filter(p => p != Success)
    failures match {
      case h :: tail =>
        val errors: Seq[Error] = failures.flatMap(f => f.asInstanceOf[Failure].errors)
        Failure(errors)
      case Nil => Success
    }
  }
}
