package ch4

import cats.data.State


class PostOrderCalculator {

}

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = State { l =>
    sym match {
      case "+" =>
        val (first :: second :: tail) = l
        val result = first + second
        (result :: tail, result)

      case "-" =>
        val (first :: second :: tail) = l
        val result = first - second
        (result :: tail, result)

      case "*" =>
        val (first :: second :: tail) = l
        val result = first * second
        (result :: tail, result)

      case "/" =>
        val (first :: second :: tail) = l
        val result = first / second
        (result :: tail, result)

      case _ =>
        val i = sym.toInt
        (i :: l, i)
    }
  }

  def point: CalcState[Int] = State { l =>
    (l, 0)
  }


  def evalAll(input: List[String]): CalcState[Int] = {
    input.foldLeft(point)((oldState, sym) => oldState.flatMap(_ => evalOne(sym)))
  }


}
