package ch4

import cats.Eval

object MyEval {

  def foldRight[A, B](as: List[Eval[A]], acc: Eval[B])(fn: (Eval[A], Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRight(tail, acc)(fn)))
      case Nil =>
        acc
    }

}
