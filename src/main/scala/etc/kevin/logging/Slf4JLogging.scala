package etc.kevin.logging

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

trait Slf4JLogging extends Logging[Logger]{
  protected val logger: Logger = Logger(LoggerFactory.getLogger(getClass.getName))
}

trait SimpleSlf4JLogging extends Slf4JLogging with SimpleLogging[Logger] {
  def error(message: String): Unit = logger.error(message)
  def error(message: String, cause: Throwable): Unit = logger.error(message, cause)
  def error(message: String, args: Any*): Unit = logger.error(message, args)

  def warn(message: String): Unit = logger.warn(message)
  def warn(message: String, cause: Throwable): Unit = logger.warn(message, cause)
  def warn(message: String, args: Any*): Unit  = logger.warn(message, args)

  def info(message: String): Unit = logger.info(message)
  def info(message: String, cause: Throwable): Unit = logger.info(message, cause)
  def info(message: String, args: Any*): Unit  = logger.info(message, args)

  def debug(message: String): Unit = logger.debug(message)
  def debug(message: String, cause: Throwable): Unit = logger.debug(message, cause)
  def debug(message: String, args: Any*): Unit  = logger.debug(message, args)

  def trace(message: String): Unit = logger.trace(message)
  def trace(message: String, cause: Throwable): Unit = logger.trace(message, cause)
  def trace(message: String, args: Any*): Unit  = logger.trace(message, args)
}
