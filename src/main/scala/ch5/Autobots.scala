package ch5

import cats.data.EitherT

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import cats.implicits._

import scala.concurrent.duration.Duration

object Autobots {

  type Response[A] = EitherT[Future, String, A]

  val powerLevels = Map(
    "Jazz"      -> 6,
    "Bumblebee" -> 8,
    "Hot Rod"   -> 10
  )

  def getPowerLevel(autobot: String) = EitherT(Future {
    powerLevels.get(autobot) match {
      case Some(i) => Right(i)
      case None => Left(s"$autobot unreachable")
    }
  })

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] = {
    for {
      a <- getPowerLevel(ally1)
      b <- getPowerLevel(ally2)
    } yield if(a + b > 15) true else false
  }

  def tacticalReport(ally1: String,
                     ally2: String
                    ): String = {
    val can = canSpecialMove(ally1, ally2)
    val future = can.value.map { either =>
      either match {
        case Right(b) => if(b) s"$ally1 and $ally2 are ready to roll out!" else s"$ally1 and $ally2 need a recharge."
        case Left(error) => error
      }
    }

    Await.result(future, Duration.Inf)
  }
}
