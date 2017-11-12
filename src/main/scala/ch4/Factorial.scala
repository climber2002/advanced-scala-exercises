package ch4

import cats.data.Writer
import cats.syntax.writer._


object Factorial {

  def slowly[A](body: => A) =
    try body finally Thread.sleep(100)

//  def factorial(n: Int): Int = {
//    val ans = slowly(if(n == 0) 1 else n * factorial(n - 1))
//    println(s"fact $n $ans")
//    ans
//  }

//  def factorial2(n: Int): Int = {
//    def loop(writer: Writer[Vector[String], Int]): Writer[Vector[String], Int] = {
//      for {
//        n <- writer
//        ans = slowly(if(n == 0) 1 else n * loop(n - 1))
//        _ <- Vector(s"fact $n $ans").tell
//      } yield ans
//    }


//  }
}
