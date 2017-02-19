package etc

import scala.util.{Failure, Success, Try}

object Try extends App {
  case class User(id: Long, name: String)

  def validate(id: Long): Boolean = id > 0

  def createUser(id: Long, name: String): User =
    if (validate(id))
      User(id, name)
    else
      throw new IllegalArgumentException(s"Invalid id. $id")

  def createUser2(id: Long, name: String): Try[User] =
    if (validate(id))
      Success(User(id, name))
    else
      Failure(new IllegalArgumentException(s"Invalid id. $id"))

  val kevin = createUser2(1L, "kevin")

  kevin match {
    case Success(User(id, name)) => println(s"The user with id $id is created. name $name")
    case Failure(e) => System.err.println(s"There is a problem in the input. Exception $e")
  }

  val tom = createUser2(0, "Tom")
  tom match {
    case Success(User(id, name)) => println(s"The user with id $id is created. name $name")
    case Failure(e) => println(s"There is a problem in the input. Exception $e")
  }

}
