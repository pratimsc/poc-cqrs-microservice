package service.command

import java.util.UUID


/**
  * A trait representing result of command processing
  */
sealed trait Result

/**
  * Error class encapsulating errors during processing of a command or rules
  *
  * @param uUID
  * @param code
  * @param title
  * @param detail
  */
sealed case class Error(uUID: UUID, code: String, title: String, detail: String)

/**
  * Failure class encapsulating all the errors that occurred during processing
  *
  * @param errors
  */
case class Failure(errors: Seq[Error]) extends Result

/**
  * Success object representing the successful processing of a Command or Rule
  */

object Success extends Result
