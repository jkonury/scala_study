package etc.kevin.logging

trait Logging[T] {
  protected def logger: T
}

trait SimpleLogging[T] extends Logging[T] {
  def error(message: String): Unit
  def error(message: String, cause: Throwable): Unit
  def error(message: String, args: Any*): Unit

  def warn(message: String): Unit
  def warn(message: String, cause: Throwable): Unit
  def warn(message: String, args: Any*): Unit

  def info(message: String): Unit
  def info(message: String, cause: Throwable): Unit
  def info(message: String, args: Any*): Unit

  def debug(message: String): Unit
  def debug(message: String, cause: Throwable): Unit
  def debug(message: String, args: Any*): Unit

  def trace(message: String): Unit
  def trace(message: String, cause: Throwable): Unit
  def trace(message: String, args: Any*): Unit
}