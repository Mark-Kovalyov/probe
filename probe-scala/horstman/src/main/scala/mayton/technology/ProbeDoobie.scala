package mayton.technology

// mayton.technology.ProbeDoobie

import doobie._
import doobie.implicits._
import cats.effect.{ContextShift, IO}

import scala.concurrent.ExecutionContext

object ProbeDoobie extends App {

  println("Start")

  implicit val contextShift : ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  val connection = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql:dht",
    "user",
    "cyberman"
  )

  case class Person(id: String, hasPermId: String)

  def find(n: String): ConnectionIO[Option[Person]] =
    sql"select id, has_perm_id from person where id = $n".query[Person].option

  // ConnectionIO[Option[Person]]

  val res  = find("1-10010166").transact(connection).unsafeRunSync

  println("OK!")

}
