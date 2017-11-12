package ch2

import cats.instances.int._
import cats.instances.option._
import cats.instances.double._
import cats.Monoid

object SuperAdder {

  def add(items: List[Int]): Int =
    items.fold(Monoid[Int].empty)(Monoid[Int].combine)

  def add(items: List[Option[Int]]): Option[Int] =
    items.fold(Monoid[Option[Int]].empty)(Monoid[Option[Int]].combine)

  case class Order(totalCost: Double, quantity: Double)

  val orderMonoid = new Monoid[Order] {
    def empty: Order = Order(Monoid[Double].empty, Monoid[Double].empty)

    def combine(o1: Order, o2: Order) = Order(Monoid[Double].combine(o1.totalCost, o2.totalCost),
      Monoid[Double].combine(o1.quantity, o2.quantity))
  }

  def add(orders: List[Order]): Order =
    orders.foldLeft(orderMonoid.empty)(orderMonoid.combine)
}
