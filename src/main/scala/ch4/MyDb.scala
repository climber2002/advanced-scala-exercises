package ch4

import cats.data.Reader

object MyDb {

  case class Db(
                 usernames: Map[Int, String],
                 passwords: Map[String, String]
               )

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] = {
//    DbReader[Option[String]](db: Db => db.usernames.get(userId))
    Reader[Db, Option[String]](db => db.usernames.get(userId))
  }

  def checkPassword(
                     username: String,
                     password: String
                   ): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): Reader[Db, Boolean] = {
    for {
      maybeUsername <- findUsername(userId)
      username = maybeUsername.getOrElse("")
      passwordValid <- checkPassword(username, password)
    } yield passwordValid
  }
    
}
