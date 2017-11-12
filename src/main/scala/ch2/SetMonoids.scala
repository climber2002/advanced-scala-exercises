package ch2

import cats.Monoid

object SetMonoids {

  implicit def unionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
    def empty: Set[A] = Set.empty

    def combine(s1: Set[A], s2: Set[A]): Set[A] = s1 union s2

  }
}
