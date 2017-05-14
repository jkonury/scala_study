package etc.kevin.logging

trait DummyLogging extends Logging[AnyRef] with SimpleLogging[AnyRef] {
  protected def logger: AnyRef = ???

  override def error(message: String): Unit = ()

  override def error(message: String, cause: Throwable): Unit = ()

  override def error(message: String, args: Any*): Unit = ()

  override def warn(message: String): Unit = ()

  override def warn(message: String, cause: Throwable): Unit = ()

  override def warn(message: String, args: Any*): Unit = ()

  override def info(message: String): Unit = ()

  override def info(message: String, cause: Throwable): Unit = ()

  override def info(message: String, args: Any*): Unit = ()

  override def debug(message: String): Unit = ()

  override def debug(message: String, cause: Throwable): Unit = ()

  override def debug(message: String, args: Any*): Unit = ()

  override def trace(message: String): Unit = ()

  override def trace(message: String, cause: Throwable): Unit = ()

  override def trace(message: String, args: Any*): Unit = ()
}
