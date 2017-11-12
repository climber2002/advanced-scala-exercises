package ch2

import cats.Monoid

object BooleanMonoids {

  val andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty: Boolean = true

    def combine(b1: Boolean, b2: Boolean): Boolean = b1 && b2
  }

  val orMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty: Boolean = false

    def combine(b1: Boolean, b2: Boolean): Boolean = b1 || b2
  }
}
