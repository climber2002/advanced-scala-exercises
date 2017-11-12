package ch5

import cats.data.EitherT

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Autobots {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )

  def getPowerLevel(autobot: String) = EitherT(Future {
    powerLevels.get(autobot) match {
      case Some(i) => Right(i)
      case None => Left(s"Can't find $autobot")
    }
  })

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = {
    val result = for {
      _ <- getPowerLevel(ally1)
      _ <- getPowerLevel(ally2)
    } yield true
  }
}
